
package example.com.sampleapptab.settings;

public class MenuItem {

    private int menuId;
    private String menuName;

    public MenuItem(int menuId, String menuName) {
        this.menuId = menuId;
        this.menuName = menuName;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
