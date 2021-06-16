import javax.swing.*;
import java.io.*;

public class Impl {
   public static final int MAX_CUSTOMERS = 1000;
   
   public static void main(String[] args) {
      Customer[] customers = new Customer[MAX_CUSTOMERS];
      
      int choice = 0;
      do {
         choice = mainMenu(customers);
      } while (choice != 4);     
   }
   
   public static int mainMenu(Customer[] c) {
      String message = "1. Add a new customer\n";
      message += "2. Print Summary Report\n";
      message += "3. Generate Roster\n";
      message += "4. Exit the program\n";
      message += "Enter your selection (1-4)";
      int choice = 0;
      do {
         try {
            choice = Integer.parseInt(JOptionPane.showInputDialog(message));    
            if (choice > 4 || choice < 1) {
               throw new IllegalArgumentException("Please enter a number between 1 and 5");
            } 
            switch (choice) {
               case 1:
                  addCustomer(c);
                  break;
               case 2:
                  printSummaryReport(c);
                  break;
               case 3:
                  generateRoster(c);
                  break;
               case 4:
                  break;
            }
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 4");
         } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
         } 
      } while (choice < 1 || choice > 4);
      return choice;
   }

   public static void addCustomer(Customer[] c) {
      int currCustNum = Customer.getNumCustomers();
      
      if (currCustNum +1 > MAX_CUSTOMERS) {
         throw new IllegalArgumentException("No more customers can be added; we are at maximum capacity.");
      }
      
      Customer newCustomer = new Customer();
      
      boolean done = false;
      do {
         try {
            newCustomer.setName(JOptionPane.showInputDialog("Enter the customer's name"));
            done = true;
         } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
      } while (!done);
      
      done = false;
      do {
         try {
            newCustomer.setPhone(JOptionPane.showInputDialog("Enter the customer's phone"));
            done = true;
         } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
      } while (!done);

      done = false;
      do {
         try {
            newCustomer.setEmail(JOptionPane.showInputDialog("Enter the customer's email"));
            done = true;
         } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
      } while (!done);

      done = false;
      int choice = 0;
      do {
         try {
            choice = Integer.parseInt(JOptionPane.showInputDialog("Enter 1 if this customer gets a corporate discount, or 2 if not"));
            if (choice != 1 && choice != 2) {
               throw new IllegalArgumentException("Please enter 1 or 2");
            }
            done = true;
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number.");
         } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
      } while (!done);

      if (choice == 1) {
         newCustomer.setHasDiscount(true);
      } else {
         newCustomer.setHasDiscount(false);
      }
      
      done = false;
      choice = 0;
      do {
         try {
            choice = Integer.parseInt(JOptionPane.showInputDialog("Enter 1 for Web Server VM, 2 for File Server VM, 3 for Bitcoin VM"));
            if (choice != 1 && choice != 2 && choice != 3) {
               throw new IllegalArgumentException("Please enter 1, 2, or 3");
            }
            done = true;
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number.");
         } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
      } while (!done);

      VM newVM;
      
      if (choice == 1) {
         newVM = new WebServerVM();
         gatherWebServerInfo((WebServerVM) newVM);
      } else if (choice == 2) {
         newVM = new FileServerVM();
         gatherFileServerInfo((FileServerVM) newVM);
      } else {
         newVM = new BitcoinVM();
         gatherBitcoinInfo((BitcoinVM) newVM);
      }
      
      newCustomer.setVM(newVM);      
      c[currCustNum] = newCustomer;
      JOptionPane.showMessageDialog(null, newCustomer);
   }
   
   public static void gatherWebServerInfo(WebServerVM v) {
      boolean done = false;
      do {
         try {
            v.setAdditionalRam(Integer.parseInt(JOptionPane.showInputDialog("Enter the amounnt of additional RAM you'd like")));
            done = true;
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number.");
         } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
      } while (!done);
   }
   
   public static void gatherFileServerInfo(FileServerVM v) {
      boolean done = false;
      int choice = 0;
      do {
         try {
            choice = Integer.parseInt(JOptionPane.showInputDialog("Enter 0 for block storage, 1 for object storage"));
            if (choice != 0 && choice != 1) {
               throw new IllegalArgumentException("Please enter 0 or 1");
            }
            v.setStorageType(choice);
            done = true;
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number.");
         } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
      } while (!done);

      done = false;
      choice = 0;
      do {
         try {
            choice = Integer.parseInt(JOptionPane.showInputDialog("Enter 0 for SSD storage, 1 for magnetic storage"));
            if (choice != 0 && choice != 1) {
               throw new IllegalArgumentException("Please enter 0 or 1");
            }
            v.setMediaType(choice);
            done = true;
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number.");
         } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
      } while (!done);

      done = false;
      do {
         try {
            v.setAdditionalStorage(Integer.parseInt(JOptionPane.showInputDialog("Enter the amounnt of additional storage you'd like in TB")));
            done = true;
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number.");
         } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
      } while (!done);

   }
   
   public static void gatherBitcoinInfo(BitcoinVM v) {
      boolean done = false;
      int choice = 0;
      do {
         try {
            choice = Integer.parseInt(JOptionPane.showInputDialog("Enter 0 for NVIDIA GPU, 1 for AMD GPU"));
            if (choice != 0 && choice != 1) {
               throw new IllegalArgumentException("Please enter 0 or 1");
            }
            v.setGpuBrand(choice);
            done = true;
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number.");
         } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
      } while (!done);
      
      done = false;
      do {
         try {
            v.setNumGpu(Integer.parseInt(JOptionPane.showInputDialog("Enter the number of GPUs")));
            done = true;
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number.");
         } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
         }
      } while (!done);

   }
   
   public static void printSummaryReport(Customer[] c) {
      double totalFees = 0;
      int memory = 0;
      int disk = 0;
      int gpu = 0;
      int bitcoin_miners = 0;
      String report = "Number of customers: " + Customer.getNumCustomers() + "\n";
      
      for(int i=0;i<Customer.getNumCustomers();i++) {
         VM vm = c[i].getVM();
         
         if (c[i].getHasDiscount()) {
            totalFees += 0.8*vm.getMonthlyCost();
         } else {
            totalFees += vm.getMonthlyCost();
         }
         memory += vm.getRam();
         if (vm instanceof WebServerVM) {
            WebServerVM casted = (WebServerVM) vm;
            memory += casted.getAdditionalRam();
         }
         
         disk += vm.getStorage();
         if (vm instanceof FileServerVM) {
            FileServerVM casted = (FileServerVM) vm;
            disk += casted.getAdditionalStorage()*1024; // convert to GB
         }
         
         if (vm instanceof BitcoinVM) {
            BitcoinVM casted = (BitcoinVM) vm;
            gpu += casted.getNumGpu();
            bitcoin_miners++;
         }
      }
      
      double average = totalFees / Customer.getNumCustomers();
      
      report += "Total Fees: " + totalFees + "\n";
      report += "Average Cost: " + average + "\n";
      report += "Total Memory: " + memory + " GB\n";
      report += "Total disk: " + disk + " GB\n";
      report += "Number GPUs: " + gpu + "\n";
      report += "Bitcoin miners: " + bitcoin_miners;
      JOptionPane.showMessageDialog(null, report);
   }

   public static void generateRoster(Customer[] c) {
      try {
         PrintWriter pw = new PrintWriter("roster.txt");
         
         for(int i=0;i<Customer.getNumCustomers();i++) {
            pw.println(c[i].getName() + ", " + c[i].getId());
         }
         
         pw.close();
      } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e.getMessage());
      }
   }

}