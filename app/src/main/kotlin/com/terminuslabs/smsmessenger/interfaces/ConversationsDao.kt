package com.terminuslabs.smsmessenger.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.terminuslabs.smsmessenger.models.Conversation

@Dao
interface ConversationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(conversation: Conversation): Long

    @Query("SELECT * FROM conversations")
    fun getAll(): List<Conversation>

    @Query("SELECT * FROM conversations WHERE read = 0")
    fun getUnreadConversations(): List<Conversation>

    @Query("UPDATE conversations SET read = 1 WHERE thread_id = :threadId")
    fun markRead(threadId: Long)

    @Query("UPDATE conversations SET read = 0 WHERE thread_id = :threadId")
    fun markUnread(threadId: Long)

    @Query("DELETE FROM conversations WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM conversations WHERE thread_id = :threadId")
    fun deleteThreadId(threadId: Long)
}
