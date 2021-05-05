package com.streese.lab.scala.playground.proto

import com.streese.lab.scala.playground.proto.models._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class ProtoSpec extends AnyFreeSpec with Matchers {

  "Protocol Buffers V3 (proto3) does" - {

    "contain only optional fields" in {
      OptionalOnly.parseFrom(Array.emptyByteArray).a shouldBe 0
    }

    "have default values for primitives" in {
      Primitives.parseFrom(Array.emptyByteArray).unwrapped shouldBe 0
    }

    "allow you to express the absence of a primitive by wrapping it" in {
      Primitives.parseFrom(Array.emptyByteArray).wrapped shouldBe empty
    }

    "allow you to add fields" in {
      val before = AddBefore(a = 1).toByteArray
      val after  = AddAfter.parseFrom(before)
      after.a shouldBe 1
      after.b shouldBe 0
    }

    "allow you to remove fields" in {
      val before = RemoveBefore(a = 1, b = 2, c = 3).toByteArray
      val after  = RemoveAfter.parseFrom(before)
      after.b shouldBe 2
      after.c shouldBe 3
    }

    "allow you to rename fields" in {
      val before = RenameBefore(a = 1, b = 2).toByteArray
      val after  = RenameAfter.parseFrom(before)
      after.a shouldBe 1
      after.c shouldBe 2
    }

    "not prevent you to accidentally rename fields" in {
      val before = RenameAccidentalBefore(a = 1, b = 2).toByteArray
      val after  = RenameAccidentalAfter.parseFrom(before)
      after.a shouldBe 2
      after.b shouldBe 1
    }

    "not fail when evolving a schema wrongly" in {
      val before = EvolutionWrongBefore(a = 1, b = 2).toByteArray
      val after  = EvolutionWrongAfter.parseFrom(before)
      after.a shouldBe 1
      after.b shouldBe ""
    }

  }

}
