//
//  SellViewController.swift
//  MoneyAju
//
//  Created by Vitor Costa on 21/10/18.
//  Copyright © 2018 Vitor Costa. All rights reserved.
//

import UIKit
import MaterialComponents

class SellViewController: UIViewController, UITextFieldDelegate {
    
    // IBOutlets
    @IBOutlet weak var currencyLabel: UILabel!
    @IBOutlet weak var currencyButton: MDCButton!
    @IBOutlet weak var valueTextField: MDCTextField!
    @IBOutlet weak var sellButton: MDCButton!
    
    
    // MARK: Private properties
    private var valueController:MDCTextInputControllerOutlined!
    private var rates: [Rate] = []
    private var balances: [Balance] = []
    
    // MARK: Override functions
    override func viewDidLoad() {
        configureUI()
        
        //--- add UIToolBar on keyboard and Done button on UIToolBar ---//
        // Get this code in https://gist.github.com/jplazcano87/8b5d3bc89c3578e45c3e
        self.addDoneButtonOnKeyboard()
        
        getData()
    }
    
    // IBActions
    @IBAction func currencyButtonTapped(_ sender: Any) {
        showCurrencyPicker()
    }
    
    @IBAction func sellButtonTapped(_ sender: Any) {
        self.setLoading(true)
        Wallet.sell(currency: currencyLabel.text!, valueToSell: Double(valueTextField.text!.replacingOccurrences(of: ",", with: "."))!) { (result) in
            self.setLoading(false)
            if result {
                self.showAlert(message: "Money sold")
            } else {
                self.showAlert(message: "Error selling money")
            }
        }
    }
    
    
    // MARK: Private functions
    private func configureUI(){
        title = "Money Aju"
        navigationController?.navigationBar.tintColor = UIColor.black
        valueTextField.delegate = self
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
    
    private func doIHaveThisCurrency(_ currency:String) -> Bool {
        for item in balances {
            if item.currency == currency {
                return true
            }
        }
        return false
    }
    
    private func getData() {
        setLoading(true)
        NetworkHelper.getCurrencyList { (rates) in
            if rates != nil {
                self.rates = rates!
                
                FireBaseHelper.getBalances(onCompletion: { (balances) in
                    if balances != nil {
                        self.balances = balances!
                        
                        for index in stride(from: self.rates.count-1, through: 0, by: -1) {
                            if !self.doIHaveThisCurrency(self.rates[index].currency) || self.rates[index].currency == "BRL" {
                                self.rates.remove(at: index)
                            }
                        }
                        
                        if self.rates.count > 0 {
                            self.currencyLabel.text = self.rates[0].currency
                        } else {
                            self.showAlert(message: "You have to buy some money first.", viewToDismiss: self)
                        }
                        
                    } else {
                        self.showAlert(message: "Error geting rates")
                    }
                    self.setLoading(false)
                })
                
            } else {
                self.showAlert(message: "Error geting rates")
                self.setLoading(false)
            }
        }
    }
    
    private func setLoading(_ isLoading:Bool){
        currencyButton.isEnabled = !isLoading
        sellButton.isEnabled = !isLoading
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
