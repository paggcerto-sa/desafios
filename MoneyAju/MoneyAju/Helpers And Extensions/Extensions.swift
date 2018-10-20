//
//  UIViewControllerExtensions.swift
//  MoneyAju
//
//  Created by Vitor Costa on 20/10/18.
//  Copyright © 2018 Vitor Costa. All rights reserved.
//

import Foundation
import UIKit

extension UIViewController {
    
    // Show alert with given message
    func showAlert(message:String){
        let alert:UIAlertController = UIAlertController(title: "Atenção!", message: message, preferredStyle: UIAlertController.Style.alert)
        alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: { (_) in }))
        present(alert, animated: true, completion: nil)
    }
    
    // Remove back button item text keeping the icon
    func hideNextTitleButtonNavBar() {
        navigationItem.backBarButtonItem = UIBarButtonItem(title: "", style: .plain, target: nil, action: nil)
    }
}
