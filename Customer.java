public class Customer {
   private String name;
   private String id;
   private String phone;
   private String email;
   private boolean hasDiscount;
   public static final double DISCOUNT = 0.2;
   private VM vm;
   private static int numCustomers;
   
   public Customer() {
      id = "usr" + (1000+numCustomers);
      numCustomers++;
   }
   
   public String getName() { return name; }
   public String getId() { return id; }
   public String getPhone() { return phone; }
   public String getEmail() { return email; }
   public boolean getHasDiscount() { return hasDiscount; }
   public static int getNumCustomers() { return numCustomers; }
   
   public void setVM(VM v) {
      if (v instanceof WebServerVM) {
         vm = new WebServerVM((WebServerVM) v);
      }
      if (v instanceof FileServerVM) {
         vm = new FileServerVM((FileServerVM) v);
      }
      if (v instanceof BitcoinVM) {
         vm = new BitcoinVM((BitcoinVM) v);
      }
   }
   
   public VM getVM() {
      if (vm instanceof WebServerVM) {
         return new WebServerVM((WebServerVM) vm);
      }
      if (vm instanceof FileServerVM) {
         return new FileServerVM((FileServerVM) vm);
      }
      if (vm instanceof BitcoinVM) {
         return new BitcoinVM((BitcoinVM) vm);
      }
      return null;
   }
   
   public void setName(String s) { 
      if (s==null || s.trim().equals("")) {
         throw new IllegalArgumentException("name can't be blank");
      }
      name = s; 
   }
   
   public void setPhone(String s) { 
      if (s==null || s.trim().equals("")) {
         throw new IllegalArgumentException("phone can't be blank");
      }
      if (!isValidPhone(s)) {
         throw new IllegalArgumentException("phone must be in the format (XXX) XXX-XXXX");
      }
      phone = s; 
   }

   public void setEmail(String s) { 
      if (s==null || s.trim().equals("")) {
         throw new IllegalArgumentException("email can't be blank");
      }
      if (!isValidEmail(s)) {
         throw new IllegalArgumentException("invalid email address");

      }
      email = s; 
   }
   
   public void setHasDiscount(boolean s) { 
      hasDiscount = s;
   }

   private boolean isValidPhone(String s) {
      if (s.length() != 14) {
         return false;
      }
      if (s.charAt(0) != '(' && s.charAt(4) != ')') {
         return false;
      }
      for(int i=1;i<=3;i++) {
         if (!Character.isDigit(s.charAt(i))) {
            return false;
         }
      }
      for(int i=6;i<=8;i++) {
         if (!Character.isDigit(s.charAt(i))) {
            return false;
         }
      }
      if (s.charAt(9) != '-') {
         return false;
      }
      for(int i=10;i<=13;i++) {
         if (!Character.isDigit(s.charAt(i))) {
            return false;
         }
      }

      return true;
   }
   
   private boolean isValidEmail(String s) {
      int firstAt = s.indexOf("@");
      int firstPeriod = s.indexOf(".");
      // at least one @
      if (firstAt < 0) {
         return false;
      }
      // at least one .
      if (firstPeriod < 0) {
         return false;
      }
      // only one @
      if (s.indexOf("@", firstAt+1) > 0) {
         return false;
      }
      // only one .
      if (s.indexOf(".", firstPeriod+1) > 0) {
         return false;
      }
      // @ comes before .
      if (firstAt > firstPeriod) {
         return false;
      }
      
      // at least one letter or digit before @
      boolean valid = false;
      for(int i=0;i<firstAt;i++) {
         if (Character.isLetter(s.charAt(i)) || 
             Character.isDigit(s.charAt(i))) {
            valid = true;   
         }
      }
      if (!valid) {
         return false;
      }
      
      // at least one letter or digit between @ and .
      valid = false;
      for(int i=firstAt;i<firstPeriod;i++) {
         if (Character.isLetter(s.charAt(i)) || 
             Character.isDigit(s.charAt(i))) {
            valid = true;   
         }
      }
      if (!valid) {
         return false;
      }

      // at least one letter or digit after .
      valid = false;
      for(int i=firstPeriod;i<s.length();i++) {
         if (Character.isLetter(s.charAt(i)) || 
             Character.isDigit(s.charAt(i))) {
            valid = true;   
         }
      }
      if (!valid) {
         return false;
      }

      return true;
   }
   
   public String toString() {
      String discountStr = "";
      if (hasDiscount) {
         double discount = 0.8 * vm.getMonthlyCost();
         discountStr = "\nCost after discount: " + discount;
      }
      return name + "\n" +
             id + "\n" + 
             phone + "\n" + 
             email + "\n" + 
             "Discount? " + hasDiscount + "\n" +
             vm +
             discountStr;
   }
}