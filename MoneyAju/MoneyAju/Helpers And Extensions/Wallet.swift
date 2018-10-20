//
//  Wallet.swift
//  MoneyAju
//
//  Created by Vitor Costa on 20/10/18.
//  Copyright © 2018 Vitor Costa. All rights reserved.
//

import Foundation

class Wallet {
    public static func buy(currency:String, valueToBuy:Double, onCompletion: @escaping(Bool)->Void){
        var currentRate:Rate!
        var currentBRLBalance:Balance!
        var currentCurrencyBalance:Balance?
        
        NetworkHelper.getCurrencyList { (rates) in
            if rates != nil{
                for rate in rates! {
                    if rate.currency == currency {
                        currentRate = rate
                        break
                    }
                }
                FireBaseHelper.getBalances { (balances) in
                    if balances != nil {
                        for balance in balances! {
                            if balance.currency == "BRL" {
                                currentBRLBalance = balance
                            }
                            if balance.currency == currency {
                                currentCurrencyBalance = balance
                            }
                        }
                        
                        let convertedValue = valueToBuy/currentRate.value
                        if  convertedValue > currentBRLBalance.value! {
                            onCompletion(false)
                            return
                        }
                        
                        let oldCurrencyValue = (currentCurrencyBalance == nil ? 0 : currentCurrencyBalance?.value)!
                        let newBRLValue = currentBRLBalance.value! - convertedValue
                        let newCurrencyValue = oldCurrencyValue + valueToBuy
                        
                        FireBaseHelper.addBalance(currency: "BRL", valor: newBRLValue)
                        FireBaseHelper.addBalance(currency: currency, valor: newCurrencyValue)
                        
                        onCompletion(true)
                    }
                }
            }
        }
    }
    
    public static func sell(currency:String, valueToSell:Double, onCompletion: @escaping(Bool)->Void){
        var currentRate:Rate!
        var currentBRLBalance:Balance!
        var currentCurrencyBalance:Balance?
        
        NetworkHelper.getCurrencyList { (rates) in
            if rates != nil{
                for rate in rates! {
                    if rate.currency == currency {
                        currentRate = rate
                        break
                    }
                }
                FireBaseHelper.getBalances { (balances) in
                    if balances != nil {
                        for balance in balances! {
                            if balance.currency == "BRL" {
                                currentBRLBalance = balance
                            }
                            if balance.currency == currency {
                                currentCurrencyBalance = balance
                            }
                        }
                        
                        if  currentCurrencyBalance == nil || valueToSell > currentCurrencyBalance!.value! {
                            onCompletion(false)
                            return
                        }
                        
                        
                        let convertedValue = valueToSell/currentRate.value
                        let oldCurrencyValue = currentCurrencyBalance!.value!
                        let newBRLValue = currentBRLBalance.value! + convertedValue
                        let newCurrencyValue = oldCurrencyValue - valueToSell
                        
                        FireBaseHelper.addBalance(currency: "BRL", valor: newBRLValue)
                        FireBaseHelper.addBalance(currency: currency, valor: newCurrencyValue)
                        
                        onCompletion(true)
                    }
                }
            }
        }
    }
}
