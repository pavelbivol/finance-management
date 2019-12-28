package com.example.financemanagement.exception

import java.lang.Exception

class EntityAllreadyExistException : Exception {

    constructor(message: String?) : super(message)

}