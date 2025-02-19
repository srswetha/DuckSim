import ducksim.Observer

abstract class Subject {
    private var observers = ArrayList<Observer>()

    fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    fun notifyObservers() {
        for (observer in observers) {
            observer.update()
        }
    }
}