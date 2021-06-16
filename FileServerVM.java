public class FileServerVM extends VM {
   private int storageType;
   public static final int STORAGE_BLOCK = 0;
   public static final int STORAGE_OBJECT = 1;
   
   private int mediaType;
   public static final int MEDIA_SSD = 0;
   public static final int MEDIA_MAGNETIC = 1;
   
   private int additionalStorage;
   public static final int MAX_ADD_STORAGE = 1024;
   
   public static final int SSD_MONTHLY = 5;
   public static final int MAG_MONTHLY = 2;
   
   public int getStorageType() { return storageType; }
   public int getMediaType() { return mediaType; }
   public int getAdditionalStorage() { return additionalStorage; }
   
   public FileServerVM() {
   }
   
   public FileServerVM(FileServerVM v) {
      this.storageType = v.getStorageType();
      this.mediaType = v.getMediaType();
      this.additionalStorage = v.getAdditionalStorage();
      computeMonthlyCost();
   }
   
   public void setStorageType(int t) {
      if (t != STORAGE_BLOCK && t != STORAGE_OBJECT) {
         throw new IllegalArgumentException("storage type should be block or object");
      }
      storageType = t;
   }
   
   public void setMediaType(int t) {
      if (t != MEDIA_SSD && t != MEDIA_MAGNETIC) {
         throw new IllegalArgumentException("media type should be ssd or magnetic");
      }
      mediaType = t;
   }

   public void setAdditionalStorage(int s) {
      if (s < 0 || s > MAX_ADD_STORAGE) {
         throw new IllegalArgumentException("additional storage should be between 0 and " + MAX_ADD_STORAGE + " TB");
      }
      additionalStorage = s;
   }
   
   public void computeMonthlyCost() {
      int base = super.getMonthlyCost();
      int newCost;
      if (mediaType == MEDIA_SSD) {
         newCost = additionalStorage * SSD_MONTHLY;
      } else {
         newCost = additionalStorage * MAG_MONTHLY;
      }
      super.setMonthlyCost(base + newCost);
   }
   
   public String toString() {
      String s =  "File Server VM\n" + super.toString() + "\n";
      if (storageType == 0) {
         s += "Storage Type: Block\n";
      } else {
         s += "Storage Type: Object\n";
      }
      if (mediaType == 0) {
         s += "Media Type: SSD\n";
      } else {
         s += "Media Type: Magnetic\n";
      }
      s += "Additional Storage: " + additionalStorage + " TB";
      return s;

   }

}