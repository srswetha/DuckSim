package ducksim

import java.awt.Color

class RubberDuck : Duck(FlyNoWay(), QuackSqueak()) {

    override val color: Color = Color.YELLOW

    override fun display() = "Rubber"
}