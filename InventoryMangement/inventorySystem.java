import java.io.BufferedReader;
import java.io.FileReader; 
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.io.*;

public class inventorySystem {

public static boolean append = true;
public static ArrayList<String> aList; //list to store information that read from csv file.
public static int total;

public static void main(String[] args) throws IOException {
    
	boolean run=true; //run until user press 0 to exit.
		while(run){
			aList = new ArrayList<String>(); //initializing array list.
			FileReader fileReader = new FileReader("itemFile.csv"); //opening csv file
			BufferedReader bufferedReader = new BufferedReader(fileReader); //to store data into buffer from csv file.
			String line = null;
		
		while( (line = bufferedReader.readLine()) != null){ // while there is data in buffer continue read operation.
			aList.add(line); //adding each line to list
		}
		 total=Integer.parseInt(aList.get(0));//getting the total number of rows
		 
		 Product Products[] = new Product[total];
		 aList.remove(0);//removing the total number from buffer to avoid exception
		 
		bufferedReader.close();//closing the buffer
		//User options
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("Inventroy management System");System.out.println();
		System.out.println("1- Show all articles");
		System.out.println("2- Sell");
		System.out.println("3- Shopping");
		System.out.println("4- Sort by number");
		System.out.println("5- Sort by name");
		System.out.println("6- Sort by quantity");
		System.out.println("7- Sort by price");
		System.out.println("8- new Article");
		System.out.println("0- exit");
		System.out.println("---------------------------------------------------------------------");System.out.println();
		System.out.println("Your Choice ?");
		Scanner input=new Scanner(System.in);
		int choice = input.nextInt();//input from user 
        clrscr();
	 
	int i=0;
    for(String ProductString : aList) {
		
		ProductString.trim(); //removing the unneccary spaces
        String[] parts = ProductString.split(",");
        int ProductNumber = Integer.valueOf(parts[0]);
        String ProductName = parts[1];
        int ProductAmount = Integer.valueOf(parts[2]);
        int ProducPrice = Integer.valueOf(parts[3]);
        int StockPrice = ProducPrice*ProductAmount;
		// adding products to procduct array through object. 
        Products[i]= new Product(ProductNumber, ProductName, ProductAmount, ProducPrice, StockPrice); 
		
		i++;
    }

switch(choice){
    // cases according to user options
      case 0:
		run=false; // stop the program
      break;
      case 1:
		System.out.println("---------------------------------------------------------------------");
		System.out.printf("%-10s %-20s %-10s %-10s %-15s %n","Number","Name","Amount","Price","StockPrice");
		 for(Product getValue: Products){
			getValue.toPrint(); //calling toPrint function from products to print the data.
         }
		 System.out.println("---------------------------------------------------------------------");
      break;
      case 2:
	      System.out.println("---------------------------------------------------------------------");
			System.out.printf("%-10s %-20s %-10s %-10s %-15s %n","Number","Name","Amount","Price","StockPrice");
		 for(Product getValue: Products){
			getValue.toPrint(); //calling toPrint function from products to print the data.
         }
		 System.out.println("---------------------------------------------------------------------");
		  SellArticle(Products);
		//decrease
      break;
      case 3:
	     
		System.out.println("---------------------------------------------------------------------");
			System.out.printf("%-10s %-20s %-10s %-10s %-15s %n","Number","Name","Amount","Price","StockPrice");
		 for(Product getValue: Products){
			getValue.toPrint(); //calling toPrint function from products to print the data.
         }
		 System.out.println("---------------------------------------------------------------------");
		  ShopArticle(Products);
	     //increase
      break;
      case 4:
	  System.out.println("\t\t\t Sorted by Number ");
		System.out.println("---------------------------------------------------------------------");
		System.out.printf("%-10s %-20s %-10s %-10s %-15s %n","Number","Name","Amount","Price","StockPrice");
		
	    Product SortedNumber[] = SortByNumber(Products); //calling sort by number function
		for(Product getValue: SortedNumber){
			getValue.toPrint();
         }
		System.out.println("---------------------------------------------------------------------");
      break;
      case 5:
	   System.out.println("\t\t\t Sorted by Name ");
		System.out.println("---------------------------------------------------------------------");
		System.out.printf("%-10s %-20s %-10s %-10s %-15s %n","Number","Name","Amount","Price","StockPrice");
		
		Product SortedName[] = SortByName(Products);//calling sort by Products function
			for(Product getValue: SortedName){
				getValue.toPrint();
			}
		System.out.println("---------------------------------------------------------------------");	
      break;
      case 6:
	  System.out.println("\t\t\t Sorted by Quantity");
		System.out.println("---------------------------------------------------------------------");
		System.out.printf("%-10s %-20s %-10s %-10s %-15s %n","Number","Name","Amount","Price","StockPrice");
		
		  Product SortedQuantity[] = SortByQuantity(Products);//calling sort by Quantity function
			for(Product getValue: SortedQuantity){
			   getValue.toPrint();
			}
		System.out.println("---------------------------------------------------------------------");
      break;
      case 7:
	  System.out.println("\t\t\t Sorted by Price");
		System.out.println("---------------------------------------------------------------------");
		System.out.printf("%-10s %-20s %-10s %-10s %-15s %n","Number","Name","Amount","Price","StockPrice");
		
	   Product SortedPrice[] = SortByPrice(Products);//calling sort by Price function
			for(Product getValue: SortedPrice){
				getValue.toPrint();
			}
		System.out.println("---------------------------------------------------------------------");	
      break;
      case 8:
	      newArticle();
      break;
	 default:
	   System.out.println("you have entered a wrong input"); //When user eneter wrong option number
	   System.out.println("Enter Again");
	 break;
          }	
	
		}
	}
	
		public static void SellArticle(Product InventoryProducts[])throws IOException{
			Scanner input = new Scanner(System.in);
			System.out.print("Enter the number of item that you want to Sell :");
			int itemNumber=input.nextInt();
			System.out.println();
			System.out.println("Enter the quantity that want to use");
			System.out.println("Make Sure the the quantity is available in inventory");
			int itemQuantity=input.nextInt();
			boolean check=false;
			for(int i=0;i<total;i++){
				
				if((InventoryProducts[i].Number)==(itemNumber)){
					check=true;
					int newQuantity=InventoryProducts[i].Update_item_quantity(itemQuantity);
					FileReader fileReader = new FileReader("itemFile.csv");
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					String line = null;
					
					FileWriter fw = new FileWriter("tempfile.csv");
					PrintWriter pw = new PrintWriter(fw);
					
					int j=0;
					while( (line = bufferedReader.readLine()) != null){
						if(j > 0){
						line.trim(); //removing the unneccary spaces
						String[] parts = line.split(",");
						        int ProductNumber = Integer.valueOf(parts[0]);
								String ProductName = parts[1];
								int ProductAmount = Integer.valueOf(parts[2]);
								int ProducPrice = Integer.valueOf(parts[3]);
								
						if(ProductNumber==(itemNumber)){
							if(newQuantity >=0 ){
							ProductAmount=newQuantity;
							String str=""+ProductNumber+","+ProductName+","+ProductAmount+","+ProducPrice+"";
							pw.printf("%s" + "%n", str);
							}else{
								System.out.println("Sorry1 The number exceeds the available products");
								
							}
							
						}else{
							
							pw.printf("%s" + "%n", line);
						}
						

					}else
					pw.printf("%s" + "%n",total);j++;
				}
					
					pw.close();
					// writting back data from temp to main file to update original file data.
					FileReader fr = new FileReader("tempfile.csv");
					BufferedReader br = new BufferedReader(fr);
					String l = null;
					
					FileWriter fwr = new FileWriter("itemFile.csv");
					PrintWriter pwr = new PrintWriter(fwr);
					
					
					while( (l = br.readLine()) != null){
						
						pwr.printf("%s" + "%n", l);
					  
					}
					
					pwr.close();
					
						
					}
			}if(check==false){
							
					System.out.println("Item Number does not exits in inventory");

			}	
					
		}	
			
		 public static void ShopArticle(Product InventoryProducts[])throws IOException{
			Scanner input = new Scanner(System.in);
			System.out.print("Enter the number of item that you want to add :");
			int itemNumber=input.nextInt();
			System.out.println();
			System.out.println("Enter the quantity that want to increse");
			int itemQuantity=input.nextInt();
			boolean check=false;
			for(int i=0;i<total;i++){
				
				if((InventoryProducts[i].Number)==(itemNumber)){
					check=true;
					int newQuantity=InventoryProducts[i].Increase_item_quantity(itemQuantity);
					FileReader fileReader = new FileReader("itemFile.csv");
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					String line = null;
					
					FileWriter fw = new FileWriter("tempfile.csv");
					PrintWriter pw = new PrintWriter(fw);
					
					int j=0;
					while( (line = bufferedReader.readLine()) != null){
						if(j > 0){
						line.trim(); //removing the unneccary spaces
						String[] parts = line.split(",");
						        int ProductNumber = Integer.valueOf(parts[0]);
								String ProductName = parts[1];
								int ProductAmount = Integer.valueOf(parts[2]);
								int ProducPrice = Integer.valueOf(parts[3]);
								
						if(ProductNumber==(itemNumber)){
							if(newQuantity >=0 ){
							ProductAmount=newQuantity;
							String str=""+ProductNumber+","+ProductName+","+ProductAmount+","+ProducPrice+"";
							pw.printf("%s" + "%n", str);
							}else{
								System.out.println("Sorry1 The number exceeds the available products");
								
							}
							
						}else{
							
							pw.printf("%s" + "%n", line);
						}
						

					}else
					pw.printf("%s" + "%n",total);j++;
				}
					
					pw.close();
					// writting back data from temp to main file to update original file data.
					FileReader fr = new FileReader("tempfile.csv");
					BufferedReader br = new BufferedReader(fr);
					String l = null;
					
					FileWriter fwr = new FileWriter("itemFile.csv");
					PrintWriter pwr = new PrintWriter(fwr);
					
					
					while( (l = br.readLine()) != null){
						
						pwr.printf("%s" + "%n", l);
					  
					}
					
					pwr.close();
					
						
					}
			}	if(check==false){
					System.out.println("Item Number does not exits in inventory");
				}

					
				
			
			 
			 
		}
	
	
	// function for adding to new article to inventory
		public static void newArticle(){
		String newItem[] = new String[total];
		Scanner input = new Scanner(System.in);
		Scanner nameinput = new Scanner(System.in);
		// taking input from user for new item
		System.out.print("Enter Product Number: ");
		newItem[0]=input.next();
		System.out.println();
		System.out.print("Enter Product Name: ");
		newItem[1]=nameinput.nextLine();
		System.out.println();
		System.out.print("Enter Product Amount: ");
		newItem[2]=input.next();
		System.out.println();
		System.out.print("Enter Product Price: ");
		newItem[3]=input.next();
		System.out.println();
		try{
			// writing the data for new item in file
		FileWriter fileWriter = new FileWriter("itemFile.csv", append);
        PrintWriter printWriter = new PrintWriter(fileWriter);
		total++;
		printWriter.append(newItem[0]);
		printWriter.append(",");
		printWriter.append(newItem[1]);
		printWriter.append(",");
		printWriter.append(newItem[2]);
		printWriter.append(",");
		printWriter.append(newItem[3]);
		printWriter.append(",");
		printWriter.append("\n");
		printWriter.close();
		
		//writing data from main file to temp to update total number.
		FileReader fileReader = new FileReader("itemFile.csv");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = null;
		
		FileWriter fw = new FileWriter("tempfile.csv");
        PrintWriter pw = new PrintWriter(fw);
		
		int i=0;
		while( (line = bufferedReader.readLine()) != null){
			if(i==0){
			  pw.printf("%s" + "%n", total);	
			}else
			pw.printf("%s" + "%n", line);
		  i++;
		}
		
		pw.close();
		// writting back data from temp to main file to update original file data.
		FileReader fr = new FileReader("tempfile.csv");
		BufferedReader br = new BufferedReader(fr);
		String l = null;
		
		FileWriter fwr = new FileWriter("itemFile.csv");
        PrintWriter pwr = new PrintWriter(fwr);
		
		
		while( (l = br.readLine()) != null){
			
			pwr.printf("%s" + "%n", l);
		  
		}
		
		pwr.close();
		
		
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}
	
	public static Product[] SortByNumber(Product ProductsStrings[]) {
   
	Product temp,tempArray[]; tempArray= new Product[total];
	
	for(int i=0;i<total;i++){
		tempArray[i]=ProductsStrings[i];// temperory storing the data in temp array to maintain the original data.
	
	}
	for(int i=0; i < total; i++){  
                 for(int j=1; j < (total-i); j++){  
				           
                          if(tempArray[j-1].Number > tempArray[j].Number ){  
						    
                                 temp = tempArray[j-1];  
                                 tempArray[j-1] = tempArray[j];  
                                tempArray[j] = temp;
                         }  
                          
                 }  
         }
		
    return tempArray;
  }
  
  
  
  public static Product[] SortByName(Product ProductsStrings[]) {
   
	Product temp,tempArray[]; tempArray= new Product[total];
	
	for(int i=0;i<total;i++){
		tempArray[i]=ProductsStrings[i];
	
	}
	for(int i=0; i < total; i++){  
                 for(int j=1; j < (total-i); j++){  
				           
                          if(tempArray[j-1].Name.compareTo( tempArray[j].Name ) < 0){  
						         
                                 temp = tempArray[j-1];  
                                 tempArray[j-1] = tempArray[j];  
                                tempArray[j] = temp;
                         }  
                          
                 }  
         }
		
    return tempArray;
  }
  

  
	public static Product[] SortByPrice(Product ProductsStrings[]) {
 
	Product temp,tempArray[]; tempArray= new Product[total];
	
	for(int i=0;i<total;i++){
		tempArray[i]=ProductsStrings[i];
	
	}
	for(int i=0; i < total; i++){  
                 for(int j=1; j < (total-i); j++){  
                          if(tempArray[j-1].Price > tempArray[j].Price ){  
						      
                                 temp = tempArray[j-1];  
                                 tempArray[j-1] = tempArray[j];  
                                tempArray[j] = temp;
                         }  
                          
                 }  
         }
		
    return tempArray;
  }

	public static Product[] SortByQuantity(Product ProductsStrings[]) {
    
	Product temp,tempArray[]; tempArray= new Product[total];
	
	for(int i=0;i<total;i++){
		tempArray[i]=ProductsStrings[i];
	
	}
	for(int i=0; i < total; i++){  
                 for(int j=1; j < (total-i); j++){  
				         
                          if(tempArray[j-1].Amount > tempArray[j].Amount ){  
						         
                                 temp = tempArray[j-1];  
                                 tempArray[j-1] = tempArray[j];  
                                tempArray[j] = temp;
                         }  
                          
                 }  
         }
		
    return tempArray;
}
public static void clrscr(){

    //Clears Screen in java

    try {

        if (System.getProperty("os.name").contains("Windows"))

            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        else

            Runtime.getRuntime().exec("clear");

    } catch (IOException | InterruptedException ex) {}

}  

}


 class Product{

public int Number,Amount,Price,StockPrice;
public String Name;

public Product(int num, String Name, int amount, int price,int stockprice) {
    super();
    this.Number = num;
    this.Name = Name;
    this.Amount = amount;
    this.Price = price;
    this.StockPrice= stockprice;
}

	public int Update_item_quantity(int itemQuantity){
		this.Amount=this.Amount- itemQuantity;
		
		return this.Amount;
	}
	public int Increase_item_quantity(int itemQuantity){
		this.Amount=this.Amount+ itemQuantity;
		
		return this.Amount;
	}
	public void toPrint() {
		
		System.out.printf("%-10s %-20s %-10s %-10s %-15s %n",Number,Name,Amount,Price+"\u20AC",StockPrice+"\u20AC");
		
	}


}