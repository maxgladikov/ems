package org.aston.ems.user_service.dto

import lombok.Data

@Data
class UserDTO () {
    var name = ""
    var mark = 0
    constructor(name: String, mark: Int) : this() {
        this.name = name
        this.mark = mark
    }
}
