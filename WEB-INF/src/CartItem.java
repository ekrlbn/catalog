public class CartItem {
  final long serialVersionUID = 1L;
  private String imgAddress;
  private String name;
  private int price;

  public CartItem(String name, String imgAddress, int price) {
    this.name = name;
    this.imgAddress = imgAddress;
    this.price = price;
  }

  public boolean equals(CartItem cartItem) {
    if (cartItem == null || this.getClass() != cartItem.getClass())
      return false;
    return this.name.equals(cartItem.getName());
  }
  public int hashCode(){
    return this.name.hashCode();
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImgAddress() {
    return imgAddress;
  }

  public void setImgAddress(String imgAddress) {
    this.imgAddress = imgAddress;
  }
}
