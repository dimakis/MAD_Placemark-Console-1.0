package org.wit.placemark.console.main

import mu.KotlinLogging
import org.wit.placemark.console.models.PlacemarkModel

private val logger = KotlinLogging.logger {}
val placemarks = ArrayList<PlacemarkModel>()

fun main(args: Array<String>) {
    logger.info { "Launching Placemark Console App" }
    println("Placemark Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when (input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> listAllPlacemarks()
            4 -> searchPlacemark()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Placemark Console App" }
}

fun menu(): Int {

    var option: Int
    var input: String?

    println("MAIN MENU")
    println(" 1. Add Placemark")
    println(" 2. Update Placemark")
    println(" 3. List All Placemarks")
    println(" 4. Get Placemark by ID")
    println(" -99. Load dummy dataa")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}


fun addPlacemark() {
    var placemark = PlacemarkModel()
    println("Add Placemark")
    println()
    print("Enter a Title: ")
    placemark.title = readLine()!!
    println("You entered ${placemark.title} for title")

    println("Enter a Description for [${placemark.title}]: ")
    placemark.description = readLine()!!
    println("You entered ${placemark.title} for title and ${placemark.description} for description")

    if (placemark.title.isNotEmpty() && placemark.description.isNotEmpty()) {
        placemarks.add(placemark.copy())
        placemark.id++
        logger.info("Placemark Added : [ $placemark ]")
    } else
        logger.info("Placemark Not Added")
}


fun updatePlacemark() {
//    var placemark = PlacemarkModel()
    println(" 2. Update Placemark")
//    println("Pick from Placemark by ID:")

    listAllPlacemarks()

    var plcmrkId = getId()
    var placemark = search(plcmrkId)

    if (placemark != null) {
        println("\nCurrent Title is ${placemark?.title} with description ${placemark?.description}")

        print("Enter a new Title for [${placemark.title}], (or leave blank to keep): ")
        var newTitle: String
        newTitle = readLine()!!
        if (!newTitle.equals("")) placemark.title = newTitle
        println("You entered ${placemark.title} for title")

        println("Enter a new Description for [${placemark.description}], (or leave blank to keep): ")
        var newDes: String
        newDes = readLine()!!
        if (!newDes.equals("")) placemark.title = newDes
        placemark.description = readLine()!!
        println("You updated ${placemark.title} for the title and ${placemark.description} for the description")
        placemarks.add(placemark.copy())
    } else
        println("Placemark Not Updated")
}


fun listAllPlacemarks() {
    println("List All Placemarks")
    println()
    placemarks.forEach { logger.info("${it}") }
}

fun getId(): Long {
    var strId: String? // String to hold user input
    var searchId: Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long): PlacemarkModel? {
    var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == id }
    return foundPlacemark
}

fun searchPlacemark() {
    var searchId = getId()
    var plcmrk = search(searchId)

    println("Placemark chosen with ID: $searchId is: $plcmrk")
    // create Placemark object here and assign,
    // based on 'searchId' value passed to 'search()'

    // Then display details to user
}
fun dummyData() {
    placemarks.add(PlacemarkModel(1, "New York New York", "So Good They Named It Twice"))
    placemarks.add(PlacemarkModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    placemarks.add(PlacemarkModel(3, "Waterford City", "You get great Blaas Here!!"))
    println("Dummy Data loaded")
    listAllPlacemarks()
}