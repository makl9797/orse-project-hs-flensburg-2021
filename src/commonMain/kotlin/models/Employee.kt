package models

import com.benasher44.uuid.Uuid
import dev.fritz2.lenses.Lenses

@Lenses
data class Employee(val employeeId: Uuid, val employeeRole: Role)
