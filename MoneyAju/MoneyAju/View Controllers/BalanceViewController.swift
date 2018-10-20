//
//  BalanceViewController.swift
//  MoneyAju
//
//  Created by Vitor Costa on 20/10/18.
//  Copyright © 2018 Vitor Costa. All rights reserved.
//

import UIKit

class BalanceViewController: UIViewController {
    
    //MARK: Override functions
    override func viewDidLoad() {
        configureNavBar()
        FireBaseHelper.getBalances { (accountArray) in
            print("CONTAS: ", accountArray)
        }
    }
    
    //MARK: Private functions
    private func configureNavBar() {
        title = "Money Aju"
        navigationController?.navigationBar.tintColor = UIColor.black
    }
}
