public class WebServerVM extends VM {
   public static final int MAX_ADDITIONAL_RAM = 120;
   public static final int ADD_RAM_MONTHLY_RATE = 10;
   private int additionalRam;
   
   public WebServerVM() {
   
   }
   
   // copy constructor
   public WebServerVM(WebServerVM v) {
      this.additionalRam = v.getAdditionalRam(); 
      computeMonthlyCost();  
   }
   
   public int getAdditionalRam() {
      return additionalRam;
   }
   
   public void setAdditionalRam(int r) {
      if (r < 0 || r > 120) {
         throw new IllegalArgumentException("additional ram must be between 0 and 120");
      }
      if (r % 8 != 0) {
         throw new IllegalArgumentException("additional ram must be a multiple of 8");
      }
      additionalRam = r;
   }
   
   public void computeMonthlyCost() {
      int base = super.getMonthlyCost();
      int additional = additionalRam / 8 * ADD_RAM_MONTHLY_RATE;
      super.setMonthlyCost(base+additional);
   }
   
   public String toString() {
      return "Web Server VM\n" +
             super.toString() + "\n" + 
             "Additional RAM: " + additionalRam;
   }
}