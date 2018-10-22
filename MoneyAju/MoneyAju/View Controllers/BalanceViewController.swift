//
//  BalanceViewController.swift
//  MoneyAju
//
//  Created by Vitor Costa on 20/10/18.
//  Copyright © 2018 Vitor Costa. All rights reserved.
//

import UIKit

class BalanceViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    // MARK: IBOutlets
    @IBOutlet weak var brlTotalLabel: UILabel!
    @IBOutlet weak var balancesTableView: UITableView!
    
    
    // MARK: Private properties
    private var balances:[Balance] = []
    private var rates:[Rate] = []
    
    //MARK: Override functions
    override func viewDidLoad() {
        configureUI()
        getData()
    }
    
    //MARK: Private functions
    private func configureUI() {
        title = "Money Aju"
        navigationController?.navigationBar.tintColor = UIColor.black
        
        balancesTableView.dataSource = self
        balancesTableView.delegate = self
    }
    
    private func getData() {
        UIApplication.shared.isNetworkActivityIndicatorVisible = true
        FireBaseHelper.getBalances { (accountArray) in
            if accountArray != nil {
                self.balances = accountArray!
                NetworkHelper.getCurrencyList(onCompletion: { (rates) in
                    if rates != nil {
                        self.rates = rates!
                        self.calculateTotalBRL()
                        self.balancesTableView.reloadData()
                    } else {
                        self.showAlert(message: "Error geting rates.")
                        UIApplication.shared.isNetworkActivityIndicatorVisible = false
                    }
                })
                
            } else {
                self.showAlert(message: "Error geting balances.")
                UIApplication.shared.isNetworkActivityIndicatorVisible = false
            }
        }
    }
    
    private func calculateTotalBRL() {
        var total:Double = 0.0
        for balance in balances {
            total += balance.value!/getValueForRate(currency: balance.currency!)
        }
        brlTotalLabel.text = "R$ " + String(total)
        UIApplication.shared.isNetworkActivityIndicatorVisible = false
    }
    
    private func getValueForRate(currency:String) -> Double{
        for rate in rates {
            if rate.currency == currency {
                return rate.value
            }
        }
        return 0
    }
    
    // MARK: Tableview functions
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return balances.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "BalanceTableViewCell") as! BalanceTableViewCell
        let balance:Balance = balances[indexPath.row]
        
        cell.balanceLabel.text = balance.currency
        cell.valueLabel.text = "$ " + String(balance.value!)
        
        return cell
    }
}

class BalanceTableViewCell:UITableViewCell {
    @IBOutlet weak var balanceLabel: UILabel!
    @IBOutlet weak var valueLabel: UILabel!
    
}
