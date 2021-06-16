public abstract class VM {
   private int ram;
   private int storage;
   private int monthlyCost;
   public static final int BASE_RAM = 8;
   public static final int BASE_STORAGE = 20;
   public static final int BASE_MONTHLY_COST = 20;
   
   public abstract void computeMonthlyCost();
   
   public VM() {
      setRam(BASE_RAM);
      setStorage(BASE_STORAGE);
      setMonthlyCost(BASE_MONTHLY_COST);
   }
   
   public int getRam() {
      return ram;
   }
   
   public int getStorage() {
      return storage;
   }
   
   public int getMonthlyCost() {
      return monthlyCost;
   }
   
   public void setRam(int r) {
      if (r < 0) {
         throw new IllegalArgumentException("ram cannot be less than 0");
      }
      ram = r;
   }
   
   public void setStorage(int s) {
      if (s < 0) {
         throw new IllegalArgumentException("storage cannot be less than 0");
      }

      storage = s;
   }
   
   public void setMonthlyCost(int c) {
      if (c < 0) {
         throw new IllegalArgumentException("cost cannot be less than 0");
      }

      monthlyCost = c;
   }
   
   public String toString() {
      return "RAM: " + ram + "\n" +
             "Storage: " + storage + " GB\n" +
             "Monthly Fees: " + monthlyCost;
   }

}