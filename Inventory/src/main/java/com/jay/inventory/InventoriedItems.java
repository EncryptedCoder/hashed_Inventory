/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jay.inventory;
import java.util.Scanner;
import java.util.Vector;

public class InventoriedItems {

    private Vector<String> itemNAME = new Vector<>();
    private Vector<Double> itemPRICE = new Vector<>();
    private Vector<Integer> itemQUANTITY = new Vector<>();
    private Vector<Integer> itemKEY = new Vector<>();

    private void printInventory(int index, boolean doTitle) {
        if (doTitle) {
            System.out.println("Item Key\tItem Name\tItem Price\tItem Quantity\n");
        }
        System.out.println(itemKEY.elementAt(index) + "\t\t" + itemNAME.elementAt(index) + "\t\t"
                + itemPRICE.elementAt(index) + "\t\t" + itemQUANTITY.elementAt(index));
    }

    //Setters for the Items
    public void setItemNAME(String name) {
        itemNAME.add(name);
    }
    public void setItemPRICE(Double price) {
        itemPRICE.add(price);
    }
    public void setItemQUANTITY(Integer quantity) {
        itemQUANTITY.add(quantity);
    }
    public void setItemKEY(Integer key) {
        itemKEY.add(key);
    }
    
    //Create a new item in the inventory
    public void createItemInventory(String name, Double price, Integer quantity, Integer key) {
        if(!(itemNAME.contains(name) || itemKEY.contains(key))){
            setItemNAME(name);
            setItemPRICE(price);
            setItemQUANTITY(quantity);
            setItemKEY(key);
        
            printInventory(itemKEY.indexOf(key), true);
            System.out.println("Was created successfully!");
        }else{
          System.out.println("\nI'm sorry, the item name or the key matches another in the database. But");
        }
    }

    //finding item that user is searching for
    public Integer retrieveInfo(String name, Integer key) {
        int index = -1;

        if (itemKEY.contains(key)) {
            index = itemKEY.indexOf(key);
        } else if (itemNAME.contains(name)) {
            index = itemNAME.indexOf(name);
        } else {
            System.out.println("I'm sorry but I couldn't find anything to match your search...\n");
            index = -1;
        }
        if (index != -1) {
            System.out.println("I foud something matching your search!\n");
            printInventory(index, true);
        }
        return index;
    }

    //listing all items in inventory
    public void listInventory() {
        boolean isFirst = true;
        for (int i = 0; i < itemKEY.size(); i++) {
            printInventory(i, isFirst);
            isFirst = false;
        }
    }

    //making changes to item found
    public void updateItem(int index) {
        int choice = -1;
        Scanner in = new Scanner(System.in);
        String replaceString = null;
        Double replaceDouble = null;
        Integer replaceInteger = null;
        
        do {
            System.out.println("What would you like to do with this item?\n"
                    + "1:\tChange Name\n"
                    + "2:\tChange Price\n"
                    + "3:\tChange Quantity\n"
                    + "4:\tChange Key\n"
                    + "5:\tDelete Item\n"
                    + "6:\tGo Back\n");
            choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("What would you like to change " + itemNAME.elementAt(index) + " to?");
                    replaceString = in.nextLine();
                    if(!itemNAME.contains(replaceString) || replaceString.equals(itemNAME.elementAt(index))){
                        itemNAME.set(index, replaceString); 
                    }else{
                        System.out.println("I'm sorry, but "+replaceString+" already exists!\n");
                        replaceString = null;
                    }
                    break;
                case 2:
                    System.out.println("What would you like to change " + itemPRICE.elementAt(index) + " to?");
                    replaceDouble = in.nextDouble();
                    itemPRICE.set(index, replaceDouble);
                    break;
                case 3:
                    System.out.println("What would you like to change " + itemQUANTITY.elementAt(index) + " to?");
                    replaceInteger = in.nextInt();
                    itemQUANTITY.set(index, replaceInteger); 
                    in.nextLine();
                    break;
                case 4:
                    System.out.println("What would you like to change " + itemKEY.elementAt(index) + " to?");
                    replaceInteger = in.nextInt();
                    if(!itemKEY.contains(replaceInteger) && replaceInteger != itemKEY.elementAt(index)){
                        itemKEY.set(index, replaceInteger); 
                    }else{
                        System.out.println("I'm sorry, but "+replaceInteger+" already exists!\n");
                        replaceInteger = null;
                    }
                    in.nextLine();
                    break;
                case 5:
                     itemNAME.removeElementAt(index);
                     itemPRICE.removeElementAt(index);
                     itemQUANTITY.removeElementAt(index);
                     itemKEY.removeElementAt(index);
                     System.out.println("\nItem was DELETED!\n");
                     in.nextLine();
                     choice = 6;
                    break;
                default:
                    break;
            }
        } while (choice < 6);

    }

}
