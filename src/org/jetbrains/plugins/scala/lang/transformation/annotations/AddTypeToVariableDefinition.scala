package org.jetbrains.plugins.scala.lang.transformation.annotations

import com.intellij.psi.PsiElement
import org.jetbrains.plugins.scala.extensions.{&&, Parent, PsiElementExt}
import org.jetbrains.plugins.scala.lang.psi.api.base.patterns.ScReferencePattern
import org.jetbrains.plugins.scala.lang.psi.api.statements.ScVariableDefinition
import org.jetbrains.plugins.scala.lang.psi.types.result.Typeable
import org.jetbrains.plugins.scala.lang.transformation._

/**
  * @author Pavel Fatin
  */
object AddTypeToVariableDefinition extends AbstractTransformer {
  def transformation: PartialFunction[PsiElement, Unit] = {
    case (p: ScReferencePattern) && Parent(l@Parent(_: ScVariableDefinition)) && Typeable(t)
      if !l.nextSibling.exists(_.getText == ":") =>

      appendTypeAnnotation(l, t)
  }
}
