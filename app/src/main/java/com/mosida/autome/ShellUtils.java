package com.mosida.autome;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ShellUtils
{

	public static final String COMMAND_SU = "su";
	public static final String COMMAND_SH = "sh";
	public static final String COMMAND_EXIT = "exit\n";
	public static final String COMMAND_LINE_END = "\n";

	private ShellUtils()
	{
		throw new AssertionError();
	}
	
	public static CommandResult execCommand(String command)
	{
		return execCommand(command, true);
	}

	public static CommandResult execCommand(String command, boolean isRoot)
	{
		return execCommand(new String[] { command }, isRoot, true);
	}

	public static CommandResult execCommand(List<String> commands, boolean isRoot)
	{
		return execCommand(commands == null ? null : commands.toArray(new String[] {}), isRoot, true);
	}

	public static CommandResult execCommand(String[] commands, boolean isRoot)
	{
		return execCommand(commands, isRoot, true);
	}
	
	public static CommandResult execCommand(String[] commands, boolean isRoot, boolean isNeedResultMsg)
	{
		int result = -1;
		if (commands == null || commands.length == 0) { return new CommandResult(
				result, null, null); }

		Process process = null;
		BufferedReader successResult = null;
		BufferedReader errorResult = null;
		StringBuilder successMsg = null;
		StringBuilder errorMsg = null;

		DataOutputStream os = null;
		try
		{
			//ProcessBuilder pb = new ProcessBuilder().redirectErrorStream(true).command("su");
            //process = pb.start();
			process = Runtime.getRuntime().exec(isRoot ? COMMAND_SU : COMMAND_SH);
			os = new DataOutputStream(process.getOutputStream());
			for (String command : commands)
			{
				if (command == null)
				{
					continue;
				}

				Log.v("vcode", ">>># " + command);
				// donnot use os.writeBytes(commmand), avoid chinese charset
				// error
				os.write(command.getBytes());
				os.writeBytes(COMMAND_LINE_END);
				os.flush();
			}
			os.writeBytes(COMMAND_EXIT);
			os.flush();

			result = process.waitFor();
			// get command result
			if (isNeedResultMsg)
			{
				successMsg = new StringBuilder();
				errorMsg = new StringBuilder();
				successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
				errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
				String s;
				while ((s = successResult.readLine()) != null)
				{
					successMsg.append(s);
				}
				while ((s = errorResult.readLine()) != null)
				{
					errorMsg.append(s);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (os != null)
				{
					os.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			try
			{
				if (successResult != null)
				{
					successResult.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				if (errorResult != null)
				{
					errorResult.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			if (process != null)
			{
				process.destroy();
			}
		}
		
		Log.v("test", "----" + result + "----" + successMsg.toString() + "---" + errorMsg.toString());
		return new CommandResult(result, successMsg == null ? null
				: successMsg.toString(), errorMsg == null ? null
				: errorMsg.toString());
	}
	
	/*public static void execCommandWithRoot(String command)
	{
		execCommandWithRoot(new String[] {command});
	}
	
	public static void execCommandWithRoot(List<String> commands)
	{
		execCommandWithRoot(commands.toArray(new String[] {}));
	}
	
	public static void execCommandWithRoot(String[] commands)
	{
		if(commands == null || commands.length == 0)
		{
			Log.v("test", "the command is null");
			return;
		}
		
		OutputStream out = null;
        try 
        {
            ProcessBuilder pb = new ProcessBuilder().redirectErrorStream(true).command("su");
            Process p = pb.start();
            StreamReader stdoutReader = new StreamReader(p.getInputStream(), "utf-8");
            stdoutReader.start();

            out = p.getOutputStream();
            
            for(String cmd : commands)
            {
            	out.write((cmd + "\n").getBytes("utf-8"));
            }
            
            out.write("exit\n".getBytes("utf-8"));
            out.flush();

            p.waitFor();
            String result = stdoutReader.getResult();
            Log.v("test", "the command result is " + result);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
        	if(out != null)
        	{
        		try
				{
					out.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
        	}
        }
	}*/

	public static class CommandResult
	{

		/** result of command **/
		public int result;
		/** success message of command result **/
		public String successMsg;
		/** error message of command result **/
		public String errorMsg;

		public CommandResult(int result)
		{
			this.result = result;
		}

		public CommandResult(int result, String successMsg, String errorMsg)
		{
			this.result = result;
			this.successMsg = successMsg;
			this.errorMsg = errorMsg;
		}
	}
}