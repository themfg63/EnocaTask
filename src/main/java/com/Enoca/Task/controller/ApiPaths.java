package com.Enoca.Task.controller;


public class ApiPaths {
    public static class Product {
        public static final String PRODUCT = "/product";
        public static final String GET_PRODUCT = "/{id}";
        public static final String GET_ALL_PRODUCT = "/all";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update/{productId}";
        public static final String DELETE = "/delete/{productId}";
    }

    public static class Customer {
        public static final String CUSTOMER = "/customer";
        public static final String ADD = "/add";
    }

    public static class Cart {
        public static final String CART = "/cart";
        public static final String GET_CART = "/get/{id}";
        public static final String UPDATE_CART = "/updateCart";
        public static final String EMPTY_CART = "/emptyCart/{id}";
        public static final String REMOVE_PRODUCT_FROM_CART = "/{cartTempId}/{cartId}";
        public static final String ADD_PRODUCT_TO_CART = "/addProductToCart";
    }

    public static class Order {
        public static final String ORDER = "/order";
        public static final String PLACE_ORDER = "/placeOrder";
        public static final String GET_ORDER_FOR_CODE = "/getOrderForCode/{orderId}";
        public static final String GET_ALL_ORDER_FOR_CUSTOMER = "/getAllOrderForCustomer/{customerId}";
    }
}
