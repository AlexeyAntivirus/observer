package introspection.references;

/**
 * GetMethodReference - функциональный интерфейс, соответствующий сигнатуре getter`ов
 * Значение полей и переменные данного типа должны быть ссылками на геттер некоторого
 * свойства конкретного объекта типа T. Не допускается использование данного интерфейса
 * для реализации фейковых методов. Только ссылка на геттеры, реализованные в конкретном
 * объекте.
 *
 * Хорошо:
 * GetMethodReference<Device, String> getter = Battery::getType
 * Плохо:
 * GetMethodReference<Device, String> getter = (target) -> "Hello, World!"
 *
 * @param <T> Тип объекта, в котором реализован геттер, на который ссылается
 * @param <V> Тип значения свойства, который возвращает геттер
 * */
@FunctionalInterface
public interface GetMethodReference<T, V> {

	/**
	 * getValue возвращает значение поля из объекта target
	 * @param target - объект, в котором реализован геттер, на который ссылается
	 * @return значение свойства, который возвращает геттер
	 * */
	V getValue(T target);
}
