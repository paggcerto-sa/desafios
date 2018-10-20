//
//  Rates.swift
//  MoneyAju
//
//  Created by Vitor Costa on 20/10/18.
//  Copyright © 2018 Vitor Costa. All rights reserved.
//

import Foundation
import ObjectMapper

class Rate:Mappable {
    var currency:String!
    var value:Double!
    
    required init?(map: Map) {
    }
    
    func mapping(map: Map) {
        currency <- map["currency"]
        value <- map["value"]
    }
}

class RateHistory: Mappable {
    var date:String!
    var value:Double!
    
    required init?(map: Map) {
    }
    
    func mapping(map: Map) {
        date <- map["date"]
        value <- map["value"]
    }
}
