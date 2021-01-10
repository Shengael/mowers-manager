package projetal2020.utils

import org.scalatest.funsuite.AnyFunSuite
import projetal2020.models.{Lawn, Mower, Position, State}

class JsonConverterSpec extends AnyFunSuite {
  test("convertToJson should convert Position to Json") {
    val position = Position(2, 5, "W")
    val expected = """{"point":{"x":2,"y":5},"direction":"W"}"""
    assert(JsonConverter.convertToJson(position).json.toString == expected)
  }

  test("convertToJson should convert Lawn to Json") {
    val lawn = new Lawn(10, 2)
    val expected = """{"x":10,"y":2}"""
    assert(JsonConverter.convertToJson(lawn).json.toString == expected)
  }

  test("convertToJson should convert Mower to Json") {
    val mower = new Mower(Position(1, 2, "N"), Position(1, 3, "N"), "GAGAGAGAA")
    val expected =
      """{
                     |            "debut": {
                     |                "point": {
                     |                    "x": 1,
                     |                    "y": 2
                     |                },
                     |                "direction": "N"
                     |            },
                     |            "instructions": ["G","A","G","A","G","A","G","A","A"],
                     |            "fin": {
                     |                "point": {
                     |                    "x": 1,
                     |                    "y": 3
                     |                },
                     |                "direction": "N"
                     |            }
                     |        }""".stripMargin.replaceAll("\\s", "")

    assert(JsonConverter.convertToJson(mower).json.toString == expected)
  }

  test("convertToJson should convert Mowers array to Json") {
    val mowers = Array(
      new Mower(Position(1, 2, "N"), Position(1, 3, "N"), "GAGAGAGAA"),
      new Mower(Position(3, 3, "E"), Position(5, 1, "E"), "AADAADADDA")
    )

    val expected =
      """[
        |        {
        |            "debut": {
        |                "point": {
        |                    "x": 1,
        |                    "y": 2
        |                },
        |                "direction": "N"
        |            },
        |            "instructions": ["G","A","G","A","G","A","G","A","A"],
        |            "fin": {
        |                "point": {
        |                    "x": 1,
        |                    "y": 3
        |                },
        |                "direction": "N"
        |            }
        |        },
        |        {
        |            "debut": {
        |                "point": {
        |                    "x": 3,
        |                    "y": 3
        |                },
        |                "direction": "E"
        |            },
        |            "instructions": ["A","A","D","A","A","D","A","D","D","A"],
        |            "fin": {
        |                "point": {
        |                    "x": 5,
        |                    "y": 1
        |                },
        |                "direction": "E"
        |            }
        |        }
        |    ]""".stripMargin.replaceAll("\\s", "")

    assert(JsonConverter.convertToJson(mowers).json.toString == expected)
  }

  test("convertToJson should convert Settings to Json") {
    val settings = new State(
      new Lawn(5, 5),
      Array(
        new Mower(Position(1, 2, "N"), Position(1, 3, "N"), "GAGAGAGAA"),
        new Mower(Position(3, 3, "E"), Position(5, 1, "E"), "AADAADADDA")
      )
    )
    val expected =
      """{
        |    "limite": {
        |        "x": 5,
        |        "y": 5
        |    },
        |    "tondeuses": [
        |        {
        |            "debut": {
        |                "point": {
        |                    "x": 1,
        |                    "y": 2
        |                },
        |                "direction": "N"
        |            },
        |            "instructions": ["G","A","G","A","G","A","G","A","A"],
        |            "fin": {
        |                "point": {
        |                    "x": 1,
        |                    "y": 3
        |                },
        |                "direction": "N"
        |            }
        |        },
        |        {
        |            "debut": {
        |                "point": {
        |                    "x": 3,
        |                    "y": 3
        |                },
        |                "direction": "E"
        |            },
        |            "instructions": ["A","A","D","A","A","D","A","D","D","A"],
        |            "fin": {
        |                "point": {
        |                    "x": 5,
        |                    "y": 1
        |                },
        |                "direction": "E"
        |            }
        |        }
        |    ]
        |}""".stripMargin.replaceAll("\\s", "")

    assert(JsonConverter.convertToJson(settings).json.toString == expected)
  }
}
