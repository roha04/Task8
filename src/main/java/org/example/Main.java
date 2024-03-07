package org.example;

import dao.ClientService;
import db.Database;

import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        //Connection connection = Database.getInstance().getConnection();
        //connection.close();
        ClientService clientService = new ClientService();
        //System.out.println(clientService.geById(3));
        //clientService.create("SpaceX");
        //clientService.setName(5,"Samsung");
        clientService.deleteById(9);
        System.out.println(clientService.listAll().toString());
    }
}