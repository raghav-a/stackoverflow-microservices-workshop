const MenuItemsForGuest = [
    {
        title: "Home",
        url: "/",
        cName: "nav-links s-btn",
    },
    {
        title: "Products",
        url: "#",
        cName: "nav-links s-btn"
    },
    {
        title: "Login",
        url: "/login",
        cName: "nav-links s-btn"
    },
    {
        title: "Signup",
        url: "/signup",
        cName: "nav-links s-btn"
    },
    {
        title: "Profile",
        url: "/profile",
        cName: "nav-links s-btn"
    }
]

const MenuItemsForLoggedInUser = [
    {
        title: "Home",
        url: "/",
        cName: "nav-links s-btn",
    },
    {
        title: "Products",
        url: "#",
        cName: "nav-links s-btn"
    },
    {
        title: "Profile",
        url: "/profile",
        cName: "nav-links s-btn"
    }
]

export function getMenuItems(userLoggedIn) {
    if (userLoggedIn)
        return MenuItemsForLoggedInUser
    else
        return MenuItemsForGuest

}