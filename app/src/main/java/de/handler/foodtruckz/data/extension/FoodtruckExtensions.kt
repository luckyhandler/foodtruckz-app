package de.handler.foodtruckz.data.extension

import de.handler.foodtruckz.data.Foodtruck
import de.handler.foodtruckz.data.Foodtruckz
import de.handler.foodtruckz.data.OperatorsItem
import de.handler.foodtruckz.data.ToursItem
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun Foodtruckz.filterForDate(from: ZonedDateTime, to: ZonedDateTime): Foodtruckz {
    val tours = this.tours?.filter { tour: ToursItem? ->
        val startDate = ZonedDateTime.parse(tour?.start)
        startDate.isAfter(from) && startDate.isBefore(to)
    }.orEmpty()

    val operators = this.operators?.filter { operator: OperatorsItem? ->
        tours.any { toursItem -> toursItem?.operatorid == operator?.id }
    }.orEmpty()

    return this.copy(
        tours = tours,
        operators = operators,
    )
}

fun Foodtruckz.mapToModelList(): List<Foodtruck> {
    val (tours, operators, _, _, _) = this
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM", Locale.getDefault())

    return tours
        ?.mapIndexed { index, toursItem ->
            if (toursItem == null) null else Foodtruck(
                name = operators?.get(index)?.name ?: "",
                description = operators?.get(index)?.description ?: "",
                location = "" +
                        "${toursItem.location?.name}\n" +
                        "${toursItem.location?.zipcode} " +
                        "${toursItem.location?.city}, \n" +
                        "${toursItem.location?.street} " +
                        "${toursItem.location?.number}",
                time = timeFormatter.format(ZonedDateTime.parse(toursItem.start)) +
                        " - " +
                        timeFormatter.format(ZonedDateTime.parse(toursItem.end)) +
                        " (${dateFormatter.format(ZonedDateTime.parse(toursItem.start))}.)",
                url = operators?.get(index)?.name_url ?: "",
                logo = operators?.get(index)?.logo,
            )
        }?.filterNotNull()
        .orEmpty()
}