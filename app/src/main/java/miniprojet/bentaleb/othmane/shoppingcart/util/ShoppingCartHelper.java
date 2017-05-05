package miniprojet.bentaleb.othmane.shoppingcart.util;

import android.content.res.Resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import miniprojet.bentaleb.othmane.shoppingcart.R;

/**
 * Created by othmane on 30/04/2017.
 */

public class ShoppingCartHelper {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";

    private static List<Product> catalog;
    private static Map<Product, ShoppingCartEntry> cartMap = new HashMap<>();

    public static List<Product> getCatalog(Resources res){
        if(catalog == null) {
            catalog = new Vector<>();
            catalog.add(new Product("Dead or Alive", res
                    .getDrawable(R.drawable.deadoralive),
                    "Dead or Alive by Tom Clancy with Grant Blackwood", 29.99));
            catalog.add(new Product("Switch", res
                    .getDrawable(R.drawable.switchbook),
                    "Switch by Chip Heath and Dan Heath", 24.99));
            catalog.add(new Product("Watchmen", res
                    .getDrawable(R.drawable.watchmen),
                    "Watchmen by Alan Moore and Dave Gibbons", 14.99));
        }

        return catalog;
    }

    public static void setQuantity(Product product, int quantity) {

        ShoppingCartEntry curEntry = cartMap.get(product);

        if(quantity <= 0) {
            if(curEntry != null)
                removeProduct(product);
            return;
        }

        if(curEntry == null) {
            curEntry = new ShoppingCartEntry(product, quantity);
            cartMap.put(product, curEntry);
            return;
        }

        curEntry.setQuantity(quantity);
    }

    public static int getProductQuantity(Product product) {

        ShoppingCartEntry curEntry = cartMap.get(product);

        if(curEntry != null)
            return curEntry.getQuantity();

        return 0;
    }

    public static void removeProduct(Product product) {
        cartMap.remove(product);
    }

    public static List<Product> getCartList() {
        List<Product> cartList = new Vector<>(cartMap.keySet().size());
        for(Product p : cartMap.keySet()) {
            cartList.add(p);
        }

        return cartList;
    }
}
