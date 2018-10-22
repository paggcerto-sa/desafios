//
//  RatesListViewController.swift
//  MoneyAju
//
//  Created by Vitor Costa on 20/10/18.
//  Copyright © 2018 Vitor Costa. All rights reserved.
//

import UIKit
import MaterialComponents

class RatesListViewController:UIViewController, UITableViewDelegate, UITableViewDataSource, UITextFieldDelegate {
    
    // MARK: IBOutlets
    @IBOutlet weak var currencyLabel: UILabel!
    @IBOutlet weak var resultTableView: UITableView!
    @IBOutlet weak var currencyButton: MDCButton!
    @IBOutlet weak var fromDateTextField: MDCTextField!
    @IBOutlet weak var toDateTextField: MDCTextField!
    @IBOutlet weak var getHistoryButton: MDCButton!
    
    // MARK: Private properties
    private var rates: [Rate] = []
    private var result: [RateHistory] = []
    private var toDateController:MDCTextInputControllerOutlined!
    private var fromDateController:MDCTextInputControllerOutlined!
    
    //MARK: Override functions
    override func viewDidLoad() {
        configureUI()
        getData()
    }
    
    // MARK: IBActions
    @IBAction func currencyButtonTapped(_ sender: Any) {
        showCurrencyPicker()
    }
    
    @IBAction func getHistoryButtonTapped(_ sender: Any) {
        if
            !fromDateTextField.text!.matches("([0-9]){4}-([0-9]){2}-([0-9]){2}")
                ||
            !toDateTextField.text!.matches("([0-9]){4}-([0-9]){2}-([0-9]){2}")
        {
            showAlert(message: "Use date as yyyy-mm-dd")
            return
        }
        setLoading(true)
        NetworkHelper.getCurrencyHistory(currency: currencyLabel.text!, fromDate: fromDateTextField.text!, toDate: toDateTextField.text!) { (rateHistoryArray) in
            if rateHistoryArray != nil {
                self.result = rateHistoryArray!
            }
            self.resultTableView.reloadData()
            self.setLoading(false)
        }
    }
    
    
    //MARK: Private functions
    private func configureUI() {
        title = "Money Aju"
        navigationController?.navigationBar.tintColor = UIColor.black
        
        resultTableView.delegate = self
        resultTableView.dataSource = self
        
        
        toDateController = MDCTextInputControllerOutlined(textInput: toDateTextField)
        fromDateController = MDCTextInputControllerOutlined(textInput: fromDateTextField)
        toDateTextField.delegate = self
        fromDateTextField.delegate = self
    }
    
    private func getData() {
        setLoading(true)
        NetworkHelper.getCurrencyList { (rates) in
            if rates != nil {
                self.rates = rates!
                if self.rates.count > 0 {
                    self.currencyLabel.text = self.rates[0].currency
                }
            }
            self.setLoading(false)
        }
    }
    
    private func setLoading(_ isLoading:Bool){
        currencyButton.isEnabled = !isLoading
        getHistoryButton.isEnabled = !isLoading
        toDateTextField.isEnabled = !isLoading
        fromDateTextField.isEnabled = !isLoading
        UIApplication.shared.isNetworkActivityIndicatorVisible = isLoading
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
    
    // MARK: Table view functions
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return result.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "RatesTableViewCell") as! RatesTableViewCell
        let rate:RateHistory = result[indexPath.row]
        
        cell.dateLabel.text = rate.date
        cell.valueLabel.text = String(rate.value)
        
        return cell
    }
    
    // MARK: TextField functions
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
}

class RatesTableViewCell: UITableViewCell {
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var valueLabel: UILabel!
}
