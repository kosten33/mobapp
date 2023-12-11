import java.io.File

fun main(args: Array<String>) {
    val message = EmailMessage("nikonstantinnn@mail.ru", null, "RU", "Me")
    val outputFile = File("formatted_email.html")
    outputFile.writeText(message.toHTML())
}

class EmailMessage(val address: String?, val topic: String?, val country: String?, val from: String?) {
    fun toHTML(): String {
        val tableContent = buildString {
            address?.let { append("<tr><td>Email Address</td><td>$it</td></tr>\n") }
            topic?.let { append("<tr><td>Topic</td><td>$it</td></tr>\n") }
            country?.let { append("<tr><td>Country</td><td>$it</td></tr>\n") }
            from?.let { append("<tr><td>Sender</td><td>$it</td></tr>\n") }
        }

        return if (tableContent.isNotEmpty()) {
            """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Email Details</title>
                <style>
                    table {
                        font-family: "Arial", sans-serif;
                        border-collapse: collapse;
                        width: 60%;
                        margin: 20px;
                    }
                    th, td {
                        border: 1px solid #333;
                        padding: 12px;
                        text-align: left;
                        font-family: "Roboto", sans-serif;
                    }
                    th {
                        background-color: #4CAF50;
                        color: #fff;
                    }
                    tr:nth-child(even) {
                        background-color: #f2f2f2;
                    }
                    tr:nth-child(odd) {
                        background-color: #dddddd;
                    }
                </style>
            </head>
            <body>
            <table>$tableContent</table>
            </body>
            </html>
            """.trimIndent()
        } else {
            ""
        }
    }
}
