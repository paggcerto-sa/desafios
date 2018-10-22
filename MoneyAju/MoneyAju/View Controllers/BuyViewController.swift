//
//  BuyViewController.swift
//  MoneyAju
//
//  Created by Vitor Costa on 21/10/18.
//  Copyright © 2018 Vitor Costa. All rights reserved.
//

import UIKit
import MaterialComponents

class BuyViewController: UIViewController, UITextFieldDelegate {
    
    // IBOutlets
    @IBOutlet weak var currencyLabel: UILabel!
    @IBOutlet weak var currencyButton: MDCButton!
    @IBOutlet weak var valueTextField: MDCTextField!
    @IBOutlet weak var buyButton: MDCButton!
    
    // MARK: Private properties
    private var valueController:MDCTextInputControllerOutlined!
    private var rates: [Rate] = []
    
    // MARK: Override functions
    override func viewDidLoad() {
        configureMaterialComponents()
        valueTextField.delegate = self
        
        //--- add UIToolBar on keyboard and Done button on UIToolBar ---//
        // Get this code in https://gist.github.com/jplazcano87/8b5d3bc89c3578e45c3e
        self.addDoneButtonOnKeyboard()
        
        getData()
    }
    
    // IBActions
    @IBAction func currencyButtonTapped(_ sender: Any) {
        showCurrencyPicker()
    }
    
    @IBAction func buyButtonTapped(_ sender: Any) {
        self.setLoading(true)
        Wallet.buy(currency: currencyLabel.text!, valueToBuy: Double(valueTextField.text!.replacingOccurrences(of: ",", with: "."))!) { (result) in
            self.setLoading(false)
            if result {
                self.showAlert(message: "Money bought")
            } else {
                self.showAlert(message: "Error buying money")
            }
        }
    }
    
    // MARK: Private functions
    private func configureMaterialComponents(){
        valueController = MDCTextInputControllerOutlined(textInput: valueTextField)
    }
    
    private func showCurrencyPicker() {
        var pickerData:[[String:String]] = []
        
        for rate in rates {
            pickerData.append(["value":rate.currency, "display":rate.currency])
        }
        
        let picker:PickerDialog = PickerDialog()
        
        picker.show(title:"Currency", options: pickerData) {
            (value) -> Void in
            self.currencyLabel.text = value
        }
    }
    
    private func getData() {
        setLoading(true)
        NetworkHelper.getCurrencyList { (rates) in
            if rates != nil {
                self.rates = rates!
                for i in 0 ..< self.rates.count {
                    if self.rates[i].currency == "BRL" {
                        self.rates.remove(at: i)
                        break
                    }
                }
                self.currencyLabel.text = self.rates[0].currency
            } else {
                self.showAlert(message: "Error geting rates")
            }
            self.setLoading(false)
        }
    }
    
    private func setLoading(_ isLoading:Bool){
        currencyButton.isEnabled = !isLoading
        buyButton.isEnabled = !isLoading
        valueTextField.isEnabled = !isLoading
        UIApplication.shared.isNetworkActivityIndicatorVisible = isLoading
    }
    
    // Get this code in https://gist.github.com/jplazcano87/8b5d3bc89c3578e45c3e
    private func addDoneButtonOnKeyboard() {
        let doneToolbar: UIToolbar = UIToolbar(frame: CGRect(x: 0, y: 0, width: 320, height: 50))
        doneToolbar.barStyle = UIBarStyle.default
        
        let flexSpace = UIBarButtonItem(barButtonSystemItem: UIBarButtonItem.SystemItem.flexibleSpace, target: nil, action: nil)
        let done: UIBarButtonItem = UIBarButtonItem(title: "Done", style: UIBarButtonItem.Style.done, target: self, action: #selector(doneButtonAction))
        
        var items = [UIBarButtonItem]()
        items.append(flexSpace)
        items.append(done)
        
        doneToolbar.items = items
        doneToolbar.sizeToFit()
        
        valueTextField.inputAccessoryView = doneToolbar
        
    }
    
    @objc private func doneButtonAction() {
        valueTextField.resignFirstResponder()
    }
    
    // MARK: TextField functions
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
}
