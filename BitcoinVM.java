public class BitcoinVM extends VM {
   private int numGpu;
   private int gpuBrand;
   public static final int GPU_NVIDIA = 0;
   public static final int GPU_AMD = 1;
   public static final int MAX_GPUS = 8;
   public static final int GPU_MONTHLY = 10;
   public static final int NVIDIA_RATE = 15;
   
   public BitcoinVM() {
   }
   
   // copy constructor
   public BitcoinVM(BitcoinVM v) {
      this.numGpu = v.getNumGpu();
      this.gpuBrand = v.getGpuBrand();
      computeMonthlyCost();
   }
   
   public int getNumGpu() { return numGpu; }
   public int getGpuBrand() { return gpuBrand; }
   
   public void setNumGpu(int i) {
      if (i<0 || i>MAX_GPUS) {
         throw new IllegalArgumentException("num GPUs must be between 0 and " + MAX_GPUS);
      }
      numGpu = i;
   }
   
   public void setGpuBrand(int i) {
      if (i != GPU_NVIDIA && i != GPU_AMD) {
         throw new IllegalArgumentException("GPU must be NVIDIA or AMD");
      }
      gpuBrand = i;
   }
   
   public void computeMonthlyCost() {
      int base = super.getMonthlyCost();
      int newCost = numGpu * GPU_MONTHLY;
      if (gpuBrand == GPU_NVIDIA) {
         newCost += NVIDIA_RATE;
      }
      super.setMonthlyCost(base + newCost);
   }
   
   public String toString() {
      String s =  "Bitcoin VM\n" + super.toString() + "\n";
      if (gpuBrand == 0) {
         s += "GPU: Nvidia";
      } else {
         s += "GPU: AMD";
      }
      s += "Number of GPUs: " + numGpu;
      return s;
   }
}