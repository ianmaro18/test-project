using System;
using System.Collections.Generic;
using System.Text;

namespace Application
{
	public class Greeter
	{
		public string SayHello()
		{
			return SayHelloName("World");
		}

		public string SayHelloName(string name)
		{
			return $"Hello {name}!";
		}
	}
}