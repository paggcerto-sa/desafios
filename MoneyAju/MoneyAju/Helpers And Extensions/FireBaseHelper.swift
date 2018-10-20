//
//  FireBaseHelper.swift
//  MoneyAju
//
//  Created by Vitor Costa on 19/10/18.
//  Copyright © 2018 Vitor Costa. All rights reserved.
//

import Foundation
import Firebase
import FirebaseDatabase
import ObjectMapper

class FireBaseHelper {
    public static func register(email:String, password:String, onCompletion:@escaping ((Bool) -> Void)) {
        Auth.auth().createUser(withEmail: email, password: password) { (authResult, error) in
            if error == nil {
                self.addBalanco(currency: "BRL", valor: 10000.00)
            }
            onCompletion(error == nil)
        }
    }
    
    public static func login(email:String, password:String, onCompletion:@escaping ((Bool) -> Void)) {
        Auth.auth().signIn(withEmail: email, password: password) { (user, error) in
            onCompletion(error == nil)
        }
    }
    
    public static func logout(onCompletion:@escaping ((Bool) -> Void)) {
        let firebaseAuth = Auth.auth()
        do {
            try firebaseAuth.signOut()
            onCompletion(true)
        } catch let signOutError as NSError {
            print ("Error signing out: ", signOutError)
            onCompletion(false)
        }
    }
    
    public static func addBalanco(currency:String, valor:Float) {
        let userID = Auth.auth().currentUser?.uid
        var ref: DatabaseReference!
        ref = Database.database().reference()
        let postRef = ref.child("accounts").child(userID!).child("balances").childByAutoId()
        let novoValor:[String:Any] = ["currency":currency, "value":valor]
        postRef.setValue(novoValor)
    }
    
    public static func getBalances(onCompletion:@escaping (([String : Balance]?) -> Void)) {
        let userID = Auth.auth().currentUser?.uid
        var ref: DatabaseReference!
        ref = Database.database().reference()
        ref.child("accounts").child(userID!).child("balances").observeSingleEvent(of: .value, with: { (snapshot) in
            if let arrayItens = snapshot.value as? NSDictionary {
                let balances = Mapper<Balance>().mapDictionary(JSON: arrayItens as! [String : [String : Any]])
                onCompletion(balances)
            } else {
                onCompletion(nil)
            }
        }) { (error) in
            print(error.localizedDescription)
            onCompletion(nil)
        }
    }
    
    public static func isLogged() -> Bool {
        return Auth.auth().currentUser != nil
    }
}
