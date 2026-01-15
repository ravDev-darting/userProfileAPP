package com.example.userprofileapp.data.remote.mapper

import com.example.userprofileapp.data.remote.model.UserDto
import com.example.userprofileapp.domain.model.User
import com.example.userprofileapp.domain.model.Address
import com.example.userprofileapp.domain.model.Geo

fun UserDto.toDomain(): User {
    return User(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website,
        address = Address(
            street = address.street,
            suite = address.suite,
            city = address.city,
            zipcode = address.zipcode,
            geo = Geo(
                lat = address.geo.lat,
                lng = address.geo.lng
            )
        )
    )
}
