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
    func showAlert(message:String, viewToDismiss:UIViewController? = nil){
        let alert:UIAlertController = UIAlertController(title: "Warning!", message: message, preferredStyle: UIAlertController.Style.alert)
        alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: { (_) in
            if viewToDismiss != nil {
                viewToDismiss?.navigationController?.popViewController(animated: true)
            }
        }))
        present(alert, animated: true, completion: nil)
    }
    
    // Remove back button item text keeping the icon
    func hideNextTitleButtonNavBar() {
        navigationItem.backBarButtonItem = UIBarButtonItem(title: "", style: .plain, target: nil, action: nil)
    }
}

extension String {
    func matches(_ regex: String) -> Bool {
        return self.range(of: regex, options: .regularExpression, range: nil, locale: nil) != nil
    }
}
