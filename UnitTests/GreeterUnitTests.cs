using Application;
using System;
using Xunit;

namespace UnitTests
{
	public class GreeterUnitTests
	{
		[Fact]
		public void SayHello_Test()
		{
			var sut = new Greeter();
			var actual = sut.SayHello();
			Assert.Equal("Hello World!", actual);
		}

	}
}