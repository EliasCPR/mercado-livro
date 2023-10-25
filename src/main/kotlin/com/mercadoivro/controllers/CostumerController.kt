package com.mercadoivro.controllers

import com.mercadoivro.dtos.CostumerDTO
import com.mercadoivro.models.CostumerModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController
@RequestMapping("/costumers")
class CostumerController {
    val costumers = mutableListOf<CostumerModel >()
    @GetMapping("/")
    fun findAllCostumers(): ResponseEntity<MutableList<CostumerModel>> {
        return ResponseEntity.status(HttpStatus.OK).body(costumers)
    }
    @GetMapping("/{id}")
    fun findCostumerById(@PathVariable id: String): ResponseEntity<CostumerModel> {
        val costumer = costumers.filter { it.id == id }.first()

        return ResponseEntity.status(HttpStatus.OK).body(costumer)
    }
    @PutMapping("/{id}")
    fun updateCostumerById(@PathVariable id: String, @RequestBody costumer: CostumerDTO): ResponseEntity.BodyBuilder {
        costumers.filter { it.id == id }.first().let {
            it.name = costumer.name
            it.email = costumer.email
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
    }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCostumer(@RequestBody costumer: CostumerDTO){
        costumers.add(CostumerModel(id = uUIDGenerator(), name = costumer.name, email = costumer.email))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCostumerById(@PathVariable id: String){
        costumers.removeIf { it.id == id }
    }
}

fun uUIDGenerator(): String {
    return UUID.randomUUID().toString()
}