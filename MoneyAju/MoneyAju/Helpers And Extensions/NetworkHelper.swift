//
//  NetworkHelper.swift
//  MoneyAju
//
//  Created by Vitor Costa on 20/10/18.
//  Copyright © 2018 Vitor Costa. All rights reserved.
//

import Foundation
import Alamofire
import ObjectMapper

enum URL:String {
    case RateList = "https://api.exchangeratesapi.io/latest?base=BRL"
    case RateHistory = "https://api.exchangeratesapi.io/history?base=BRL"
}

class NetworkHelper {
    public static func getCurrencyList(onCompletion:@escaping ([Rate]?) -> Void) {
        Alamofire.request(URL.RateList.rawValue).responseJSON { response in
            switch response.result {
            case .success(_):
                if let json = response.result.value as? [String:Any]{
                    let rates = json["rates"] as! [String:NSNumber]
                    var jsonConverted:[[String:Any]] = []
                    for (k,j) in rates {
                        jsonConverted.append(["currency":k, "value":j])
                    }
                    onCompletion(Mapper<Rate>().mapArray(JSONArray: jsonConverted))
                } else {
                    onCompletion(nil)
                }
            case .failure(_):
                onCompletion(nil)
            }
            
        }
    }
    
    public static func getCurrencyHistory(currency:String, fromDate:String, toDate:String, onCompletion:@escaping ([RateHistory]?) -> Void){
        let urlComplement = "&symbols=\(currency)&start_at=\(fromDate)&end_at=\(toDate)"
        Alamofire.request(URL.RateHistory.rawValue + urlComplement).responseJSON { response in
            switch response.result {
            case .success(_):
                if let json = response.result.value as? [String:Any]{
                    let rates = json["rates"] as! [String:[String:NSNumber]]
                    var jsonConverted:[[String:Any]] = []
                    for (k,j) in rates {
                        jsonConverted.append(["date":k, "value":(j as! [String:Double])[currency]!])
                    }
                    onCompletion(Mapper<RateHistory>().mapArray(JSONArray: jsonConverted))
                } else {
                    onCompletion(nil)
                }
            case .failure(_):
                onCompletion(nil)
            }
            
        }
    }
}
