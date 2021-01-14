package bobby.irawan.moviecatalogue.helper

import androidx.paging.PagedList
import io.mockk.every
import io.mockk.mockkClass
import org.mockito.ArgumentMatchers.anyInt

object PagedListUtil {

    @Suppress("UNCHECKED_CAST")
    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = mockkClass(PagedList::class) as PagedList<T>
        every { pagedList[anyInt()] }.answers { call ->
            val index = call.invocation.args.first() as Int
            list[index]
        }
        every { pagedList.size } returns (list.size)

        return pagedList
    }
}