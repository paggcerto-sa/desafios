//
//  HomeViewController.swift
//  MoneyAju
//
//  Created by Vitor Costa on 19/10/18.
//  Copyright © 2018 Vitor Costa. All rights reserved.
//

import UIKit
import MaterialComponents

class HomeViewController: UIViewController {
    
    // MARK: Override functions
    override func viewDidLoad() {
        hideNextTitleButtonNavBar()
    }
    
    // MARK: IBActions
    @IBAction func logoutButtonTapped(_ sender: Any) {
        FireBaseHelper.logout { (loggedOut) in
            if loggedOut {
                self.dismiss(animated: true, completion: nil)
            } else {
                self.showAlert(message: "Erro ao deslogar.")
            }
        }
    }
}
