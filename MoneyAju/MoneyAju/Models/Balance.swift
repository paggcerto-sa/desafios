//
//  Balance.swift
//  MoneyAju
//
//  Created by Vitor Costa on 20/10/18.
//  Copyright © 2018 Vitor Costa. All rights reserved.
//

import Foundation
import ObjectMapper

class Balance:Mappable {
    var currency:String? = nil
    var value:Double? = nil
    
    required init?(map: Map) {
    }
    
    func mapping(map: Map) {
        currency <- map["currency"]
        value <- map["value"]
    }
}
