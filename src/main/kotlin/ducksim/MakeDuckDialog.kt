package ducksim

import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.util.*
import javax.swing.*

class MakeDuckDialog(
    private val model: DuckPond,
    private val view: DuckSimView
) : JDialog() {

    enum class Decoration {
        STAR,
        CROSS,
        MOON
    }

    private var totalBlingCount: Int = 0
    private val blingCount = mutableMapOf(
        Decoration.STAR to 0,
        Decoration.CROSS to 0,
        Decoration.MOON to 0
    )

    private val blingCountLabel = mapOf(
        Decoration.STAR to JLabel("0"),
        Decoration.CROSS to JLabel("0"),
        Decoration.MOON to JLabel("0"),
    )

    // Duck panel
    private val duckPanel = JPanel()
    private val duckLabel = JLabel("Duck")
    private val duckStrings = listOf("Mallard", "Redhead", "Rubber", "Decoy", "Goose")
    private val duckOptions: JComboBox<*> = JComboBox<Any?>(duckStrings.toTypedArray())

    // Stored Data
    var duckType = "Mallard"

    // Bling panel
    private val blingPanel = JPanel(GridLayout(3, 4, 5, 5))

    // Button panel
    private val buttonPanel = JPanel()
    private val okayButton = JButton("Okay")
    private val cancelButton = JButton("Cancel")

    // Public Methods

    // Constructor
    init {
        this.contentPane.layout = BoxLayout(this.contentPane, BoxLayout.Y_AXIS)

        // add duck panel
        duckPanel.add(duckLabel)
        duckOptions.addActionListener { e: ActionEvent ->
            val cb = e.source as JComboBox<*>
            duckType = cb.selectedItem as String
        }
        duckPanel.add(duckOptions)
        this.add(duckPanel)

        // add Bling Panel
        // add star row
        addBlingRow(Decoration.STAR, blingPanel)
        addBlingRow(Decoration.CROSS, blingPanel)
        addBlingRow(Decoration.MOON, blingPanel)

        blingPanel.border = BorderFactory.createEmptyBorder(0, 10, 0, 0)
        this.add(blingPanel)

        // add button panel
        cancelButton.addActionListener { dispose() }
        buttonPanel.add(cancelButton)
        okayButton.addActionListener {
            // makeDuckDialog
            val duck: Duck? = when (duckType) {
                "Mallard" -> MallardDuck()
                "Redhead" -> RedheadDuck()
                "Rubber" -> RubberDuck()
                "Decoy" -> DecoyDuck()
                "Goose" -> GooseDuck(Goose())
                else -> null
            }
            if (duck != null) {
                val tempDuck = DuckFactory.createDuck(
                    duck,
                    blingCount.getValue(Decoration.STAR),
                    blingCount.getValue(Decoration.MOON),
                    blingCount.getValue(Decoration.CROSS)
                )
                model.addNewDuck(tempDuck)
            }
            view.repaint()
            dispose()
        }
        buttonPanel.add(okayButton)
        this.add(buttonPanel)
        this.pack()
        this.setLocationRelativeTo(null)
        this.isVisible = true
    }

    private fun addBlingRow(decoration: Decoration, blingPanel: JPanel) {

        blingPanel.add(JLabel(
            decoration.name.lowercase(Locale.getDefault())
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }))
        blingPanel.add(blingCountLabel[decoration])

        val incrementButton = JButton("+")
        incrementButton.addActionListener {
            val count = blingCount[decoration] ?: 0
            if (count < 3 && totalBlingCount < 3) {
                totalBlingCount++
                blingCount[decoration] = count + 1
            }
            // refresh view
            blingCountLabel[decoration]!!.text = blingCount[decoration].toString()
            blingCountLabel[decoration]!!.repaint()
        }
        blingPanel.add(incrementButton)

        val decrementButton = JButton("-")
        decrementButton.addActionListener {
            val count = blingCount[decoration] ?: 0
            if (count > 0 && totalBlingCount > 0) {
                totalBlingCount--
                blingCount[decoration] = count - 1
            }
            // refresh view
            blingCountLabel[decoration]!!.text = blingCount[decoration].toString()
            blingCountLabel[decoration]!!.repaint()
        }
        blingPanel.add(decrementButton)
    }
}
