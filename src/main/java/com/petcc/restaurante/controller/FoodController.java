package com.petcc.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcc.restaurante.model.Food;
import com.petcc.restaurante.service.FoodService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/foods")
@CrossOrigin(origins = "*")
@Tag(
    name = "FoodController",
    description = "Endpoints para gerenciamento de comidas")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Operation(
        summary = "Obter todas as comidas",
        description = "Retorna uma lista com todas as comidas disponíveis.")
    @GetMapping("/get")
    public ResponseEntity<List<Food>> getAllFoods(){
        List<Food> foods = foodService.getAllFoods();
        return new ResponseEntity<List<Food>>(foods, HttpStatus.OK);
    }
    @Operation(
        summary = "Deletar uma comida",
        description = "Deleta uma comida com base no ID fornecido.")
    @DeleteMapping("/delete")
    public ResponseEntity<Food> deleteFood(Long id){
        boolean deleted = foodService.deleteFood(id);
        if (deleted) {
            return new ResponseEntity<Food>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Food>(HttpStatus.NOT_FOUND);
        }
    }
    @Operation(
        summary = "Salvar uma comida",
        description = "Salva uma nova comida com os dados fornecidos.")
    @PostMapping("/save")
    public ResponseEntity<Food> saveFood(@RequestBody Food food){
        try {
            Food savedFood = foodService.saveFood(food);
            if (savedFood == null) {
                return new ResponseEntity<Food>(HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<Food>(savedFood, HttpStatus.CREATED);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<Food>(HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(
        summary = "Atualizar uma comida",
        description = "Atualiza os dados de uma comida existente com base no ID fornecido.")
    @PutMapping("/update")
    public ResponseEntity<Food> updateFood(@RequestBody Food food){
        try {
            Food updatedFood = foodService.updateFood(food.getId(), food.getTitle(), food.getPrice(), food.getImage());
            return new ResponseEntity<Food>(updatedFood, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<Food>(HttpStatus.NOT_FOUND);
        }
    }
}