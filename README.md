# NonLeakageTask
Due to the deprecation of AsyncTask in Android 11, I'm following the recommendation by Google to use java.util.concurrent to replace all the AsyncTask in our product. 
However, I found a lot of improper usage especially the callback design. We usually use this callback to notify our UI to update when the background task it's done.
If the background task doesn't take too long, it's totally fine. But, how do we ensure it's a short task to let it be alive more longer than our Activity. 
It could cause memory leak when it's alive after Activity finishes because the inside callback can reference to it. It's really 
painful to refine all these usages. It's ok with the background task which is alive just a little longer than Activity, and sometimes we don't want it to be interrupted 
even Activiy is finished. I have to put a lot of efforts to find out their intentions directly from the code level.
