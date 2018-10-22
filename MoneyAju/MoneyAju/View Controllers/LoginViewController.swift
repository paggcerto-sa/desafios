//
//  LoginViewController.swift
//  MoneyAju
//
//  Created by Vitor Costa on 19/10/18.
//  Copyright © 2018 Vitor Costa. All rights reserved.
//

import Foundation
import UIKit
import MaterialComponents

class LoginViewController: UIViewController, UITextFieldDelegate {
    
    // MARK: IBOutlets
    @IBOutlet weak var loginTextField: MDCTextField!
    @IBOutlet weak var passwordTextField: MDCTextField!
    @IBOutlet weak var loginButton: MDCButton!
    @IBOutlet weak var registerButton: MDCButton!
    
    // MARK: Private properties
    private var loginController:MDCTextInputControllerOutlined!
    private var passwordController:MDCTextInputControllerOutlined!
    
    // MARK: Override functions
    override func viewDidLoad() {
        configureMaterialComponents()
//        loginTextField.becomeFirstResponder()
        loginTextField.delegate = self
        passwordTextField.delegate = self
    }
    
    @IBAction func loginButtonTapped(_ sender: Any) {
        if validateFields() {
            setButtonsEnabled(false)
            UIApplication.shared.isNetworkActivityIndicatorVisible = true
            FireBaseHelper.login(email: loginTextField.text!, password: passwordTextField.text!) { (logged) in
                UIApplication.shared.isNetworkActivityIndicatorVisible = false
                self.setButtonsEnabled(true)
                if logged {
                    self.performSegue(withIdentifier: "segueToHome", sender: nil)
                } else {
                    self.showAlert(message: "Login error.")
                }
            }
        }
    }
    
    @IBAction func registerButtonTapped(_ sender: Any) {
        if validateFields() {
            setButtonsEnabled(false)
            UIApplication.shared.isNetworkActivityIndicatorVisible = true
            FireBaseHelper.register(email: loginTextField.text!, password: passwordTextField.text!) { (registered) in
                self.setButtonsEnabled(true)
                UIApplication.shared.isNetworkActivityIndicatorVisible = false
                if registered {
                    self.performSegue(withIdentifier: "segueToHome", sender: nil)
                } else {
                    self.showAlert(message: "Register error.")
                }
            }
        }
    }
    
    // MARK: Private functions
    private func configureMaterialComponents(){
        loginController = MDCTextInputControllerOutlined(textInput: loginTextField)
        passwordController = MDCTextInputControllerOutlined(textInput: passwordTextField)
    }
    
    private func setButtonsEnabled(_ enabled:Bool) {
        loginButton.isEnabled = enabled
        registerButton.isEnabled = enabled
    }
    
    // Validate fields before login or register
    private func validateFields() -> Bool{
        if let loginText = loginTextField.text, let passwordText = passwordTextField.text {
            if loginText.count <= 0 || passwordText.count <= 0 {
                showAlert(message: "Dados inválidos")
                return false
            } else {
                return true
            }
        } else {
            showAlert(message: "Dados inválidos")
            return false
        }
    }
    
    // MARK: Textfield delegate functions
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
}
