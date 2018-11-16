package introspection.references;

/**
 * SetMethodReference - функциональный интерфейс, соответствующий сигнатуре setter`ов
 * Значение полей и переменные данного типа должны быть ссылками на сеттер некоторого
 * свойства конкретного объекта типа T. Не допускается использование данного интерфейса
 * для реализации фейковых методов. Только ссылка на сеттеры, реализованные в конкретном
 * объекте.
 *
 * Хорошо:
 * SetMethodReference<Device, String> getter = Battery::setType
 * Плохо:
 * SetMethodReference<Device, String> getter = (target, value) -> {
 *     something();
 * }
 *
 * @param <T> Тип объекта, в котором реализован сеттер, на который ссылается
 * @param <V> Тип значения свойства, который передается в сеттер
 * */
@FunctionalInterface
public interface SetMethodReference<T, V> {

	/**
	 * getValue возвращает значение поля из объекта target
	 * @param target - объект, в котором реализован геттер, на который ссылается
	 * @param value - значениt свойства, который передается в сеттер
	 * */
	void setValue(T target, V value);
}
