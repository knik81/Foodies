package nti.team.foodies.ui.screens.catalog.entity

import nti.team.entity.Tags

data class CheckedFilters(
    val tags: Tags,
    var isChecked: Boolean = false
)