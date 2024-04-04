# FOODIES
Тестовое мобильное андроид приложение для заказа еды. Цель разработки приложения – это демонстрация умений разработчика.

## Содержание
- [Функции](#функции)
- [Технологии](#технологии)
- [Фичи](#фичи)
- [Доработка](#доработка)
- [Улучшения](#Улучшения)

# Функции:
- Отображение списка блюд для заказа
- Возможность просмотреть детальное описание
- Заполнение корзины заказов
- Редактировании количества товара в корзине
- Имитация отправки заказа
- Поиск блюд по названию
- Фильтр блюд
- Категории

# Технологии
Jetpack Compose, MVVM, Clean Architecture с разделением на модули, HILT, JSON, Coroutines, Navigation Compose, Lottie animation

# Фичи
- Иконка
- У элемента на главном экране отображение индикатора с количеством в корзине 
- Отображение информации при ошибках из апи (например нет интернета)

# Доработка
- Плохо реализовано скрытие боттомшит
- Отсутствуют тесты

# Улучшения
-	Улучшение функции скрытия боттомшит 
-	Добавление тестов
-	Детальнее проработать интерфейс при смене ориентации или ширины экрана. Некоторые элементы отображаются не очень элегантно
-	Отображение индикатор процесса загрузки данных из апи
-	Желательно сохранять корзину с покупками после перезагрузки приложения. Но сделано согласно ТЗ
