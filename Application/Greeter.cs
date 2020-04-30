namespace Application
{
	public class Greeter
	{
		public Greeter()
		{

		}

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