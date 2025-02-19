package ducksim

import java.awt.Color

abstract class Duck(
    private val defaultFlyBehavior: FlyBehavior = FlyWithWings(),
    private val defaultQuackBehavior: QuackBehavior = QuackNormal()) : Observer {

    // values that can be overridden

    open val color: Color = Color.BLACK

    // variables that can be changed only by functions in this class
    var flyBehavior: FlyBehavior = defaultFlyBehavior
        protected set
    var quackBehavior: QuackBehavior = defaultQuackBehavior
        protected set
    var holdFlyBehavior: FlyBehavior = flyBehavior
    var holdQuackBehavior: QuackBehavior = quackBehavior
    open var quackText: String = quackBehavior.quackText

    var state = State.SWIMMING
    var isFree = true
    var isOnDSWC = false
        private set

    // function for setting the state back to its default (swimming)

    fun swim() {
        state = State.SWIMMING
    }

    // functions from the context menu

    override fun update() {
        state = State.WELCOMING
    }

    open fun fly() {
        state = flyBehavior.state
    }

    open fun quack() {
        state = quackBehavior.state
    }

    open val joinDSCW = object : DuckMenuItem {
        override fun invoke() {
            joinWC()
        }
    }

    open val quitDSCW = object : DuckMenuItem {
        override fun invoke() {
            quitWC()
        }
    }

    fun joinWC() {
        isOnDSWC = true
        DuckFactory.registerObserver(this)
    }

    fun quitWC() {
        isOnDSWC = false
        DuckFactory.removeObserver(this)
    }

    open val capture = object : DuckMenuItem {
        override fun invoke() {
            doCapture()
        }
    }

    fun doCapture() {
        isFree = false
        flyBehavior = FlyNoWay()
        quackBehavior = QuackNoWay()
    }

    open val release = object : DuckMenuItem {
        override fun invoke() {
            doRelease()
        }
    }

    fun doRelease() {
        isFree = true
        flyBehavior = defaultFlyBehavior
        quackBehavior = defaultQuackBehavior
    }

    // abstract display function that must be implemented by concrete classes

    abstract fun display(): String
}
