package com.orgmihailo.mongodbservice.service;

import com.orgmihailo.mongodbservice.model.Expense;
import com.orgmihailo.mongodbservice.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository; 
    }

    public void addExpense(Expense expense){
        expenseRepository.insert(expense);
    }
    public void updateExpense(Expense expense){
        Expense savedExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(()-> new RuntimeException(String.format("Cannot find exepnse with that id")));

        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());

        expenseRepository.save(savedExpense);
    }
    public List<Expense> getAllExepenses(){
        return expenseRepository.findAll();
    }
    public Expense getExpenseByName(String name){
        return  expenseRepository.findByName(name)
                .orElseThrow(()-> new RuntimeException(String.format("Cannot find exepnse with that name")));
    }
    public void deleteExpense(String id){
        expenseRepository.deleteById(id);
    }

}
