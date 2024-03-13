package com.example.demo.entities;
//что если мы не хотим выдергивать из базы тяжеловесный объект, а нам надо только какие-то его поля?
//создадим интерфейс ItemProjection с методами, которые будут вытаскивать нужные поля
//далее идем в ItemRepository

public interface ItemProjection {
    String getTitle();
    int getCost();
}
